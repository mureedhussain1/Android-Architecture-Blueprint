package com.mureed.app.data.channels

import androidx.annotation.WorkerThread
import androidx.lifecycle.MutableLiveData
import com.mureed.app.data.db.MediaDao
import com.mureed.app.data.model.ChannelViewProgress
import com.mureed.app.data.model.Media
import com.mureed.app.data.model.Result
import com.mureed.app.data.model.toErrorResult
import com.mureed.app.utils.Constants
import javax.inject.Inject

open class NewEpisodeRepository @Inject constructor(
    private val dataSource: ChannelRemoteDataSource,
    private val mediaDao: MediaDao
) {

    @WorkerThread
    suspend fun fetchEpisodes(
        episodesResult: MutableLiveData<Result<List<Media>>>,
        channelViewProgress: MutableLiveData<ChannelViewProgress>
    ) {

        channelViewProgress.value?.newEpisodesProgress = 0
        channelViewProgress.postValue(channelViewProgress.value)
        try {

            val result = dataSource.getNewEpisodes()
            val episodes = result.body()?.data?.media

            if (result.isSuccessful && !episodes.isNullOrEmpty()) {
                mediaDao.deleteAllNewEpisodes()
                mediaDao.insert(episodes.onEach {
                    it.courseId = 0
                    it.seriesId = 0
                })
                episodesResult.postValue(Result.Success(episodes))
            } else {
                episodesResult.postValue(Constants.LOAD_EPISODES_ERROR.toErrorResult())
            }

        } catch (exception: Exception) {
            episodesResult.postValue(Constants.EPISODES_NETWORK_ERROR.toErrorResult())
        } finally {
            channelViewProgress.value?.newEpisodesProgress = 100
            channelViewProgress.postValue(channelViewProgress.value)
        }
    }

    fun getNewEpisodes() = mediaDao.getNewEpisodes()

}