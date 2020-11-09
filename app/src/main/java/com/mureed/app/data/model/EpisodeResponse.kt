package com.mureed.app.data.model



data class EpisodeData (
    var media: List<Media>? = listOf()
)

data class EpisodeResponse (
    var data: EpisodeData?
)