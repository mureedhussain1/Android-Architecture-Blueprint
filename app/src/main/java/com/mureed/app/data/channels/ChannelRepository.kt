package com.mureed.app.data.channels

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.mureed.app.data.db.ChannelDao
import com.mureed.app.data.db.MediaDao
import com.mureed.app.data.model.Channel
import com.mureed.app.data.model.ChannelViewProgress
import com.mureed.app.data.model.Result
import com.mureed.app.data.model.toErrorResult
import com.mureed.app.utils.Constants
import javax.inject.Inject

open class ChannelRepository @Inject constructor(
    private val dataSource: ChannelRemoteDataSource,
    private val channelDao: ChannelDao,
    private val mediaDao: MediaDao
) {

    @WorkerThread
    suspend fun fetchChannels(
        channelsResult: MutableLiveData<Result<List<Channel>>>,
        channelViewProgress: MutableLiveData<ChannelViewProgress>
    ) {
        channelViewProgress.value?.channelsProgress = 0
        channelViewProgress.postValue(channelViewProgress.value)
        try {
            val result = dataSource.getChannels()
            val channels = result.body()?.data?.channels

            if (result.isSuccessful && !channels.isNullOrEmpty()) {
                channelsResult.postValue(Result.Success(channels))
                insertChannels(channels)
            } else {
                channelsResult.postValue(Constants.LOAD_CHANNELS_ERROR.toErrorResult())
            }

        } catch (exception: Exception) {
            exception.printStackTrace()
            channelsResult.postValue(Constants.CHANNELS_NETWORK_ERROR.toErrorResult())
        } finally {
            channelViewProgress.value?.channelsProgress = 100
            channelViewProgress.postValue(channelViewProgress.value)
        }
    }

    /**
     * Inserts in database one to many relation here
     */
    private suspend fun insertChannels(channels: List<Channel>) {
        // delete existing data first since can't replace because PrimaryKey/id is not coming from server
        channelDao.deleteAll()
        mediaDao.deleteAllChannelsMedia()

        channels.forEach { channel ->
            val id = channelDao.insert(channel)
            if (!channel.series.isNullOrEmpty())
                mediaDao.insert(channel.series!!.onEach {
                    it.id = null
                    it.seriesId = id
                    it.isSeriesItem = true
                })
            if (!channel.latestMedia.isNullOrEmpty())
                mediaDao.insert(channel.latestMedia!!.onEach {
                    it.id = null
                    it.courseId = id
                    it.isSeriesItem = false
                })
        }
    }

    fun getChannels(): LiveData<List<Channel>> = Transformations.map(channelDao.getChannels()) {list->
        list.map {
            it.channel.series = it.series
            it.channel.latestMedia = it.latestMedia
            it.channel
        }
    }

}