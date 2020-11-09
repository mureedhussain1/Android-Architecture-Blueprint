package com.mureed.app.data.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Media(
    @PrimaryKey(autoGenerate = true)
    var id: Long?,
    var isSeriesItem: Boolean = false,
    var seriesId: Long = 0,
    var courseId: Long = 0,
    var type: String? = "",
    var title: String? = "",
    @Embedded
    var coverAsset: CoverAsset?,
    @Embedded(prefix = "channel")
    var channel: ChannelTitle?
)

data class ChannelTitle(
    var title: String
)

data class CoverAsset(
    var url: String? = ""
)