package com.mureed.app.view.channels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mureed.app.data.channels.CategoryRepository
import com.mureed.app.data.channels.ChannelRepository
import com.mureed.app.data.channels.NewEpisodeRepository
import com.mureed.app.data.model.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class ChannelViewModel @Inject constructor(
    private val categoryRepository: CategoryRepository,
    private val channelRepository: ChannelRepository,
    private val episodeRepository: NewEpisodeRepository
) : ViewModel() {

    private val categoriesLiveData = MutableLiveData<Result<List<Category>>>()
    val categoriesResult: LiveData<Result<List<Category>>> = categoriesLiveData

    private val episodesLiveData = MutableLiveData<Result<List<Media>>>()
    val episodesResult: LiveData<Result<List<Media>>> = episodesLiveData

    private val channelsLiveData = MutableLiveData<Result<List<Channel>>>()
    val channelsResult: LiveData<Result<List<Channel>>> = channelsLiveData

    private val channelViewProgress = MutableLiveData(ChannelViewProgress())
    val channelProgress: LiveData<ChannelViewProgress> = channelViewProgress

    init {
        loadChannelsData()
    }

    // Load reactive streams of data from local database
    val categories = categoryRepository.getCategories()
    val channels = channelRepository.getChannels()
    val episodes = episodeRepository.getNewEpisodes()

    // starts requests parallel
    fun loadChannelsData() {
        loadEpisodes()
        loadChannels()
        loadCategories()
    }

    // Fetch data from server
    fun loadCategories() {
        viewModelScope.launch(Dispatchers.IO) {
            categoryRepository.fetchCategories(categoriesLiveData, channelViewProgress)
        }
    }

    fun loadChannels() {
        viewModelScope.launch(Dispatchers.IO) {
            channelRepository.fetchChannels(channelsLiveData, channelViewProgress)
        }
    }

    fun loadEpisodes() {
        viewModelScope.launch(Dispatchers.IO) {
            episodeRepository.fetchEpisodes(episodesLiveData, channelViewProgress)
        }
    }

}