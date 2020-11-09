package com.mureed.app.data.model


data class ChannelData(
    var channels: List<Channel>? = listOf()
)

data class ChannelResponse(
    var data: ChannelData?
)