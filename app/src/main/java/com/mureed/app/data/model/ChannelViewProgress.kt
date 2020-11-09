package com.mureed.app.data.model

data class ChannelViewProgress(
    var categoriesProgress: Int = 0,
    var newEpisodesProgress: Int = 0,
    var channelsProgress: Int = 0
) {

    val totalProgress: Int
        get() = (categoriesProgress + newEpisodesProgress + channelsProgress) / 3

}