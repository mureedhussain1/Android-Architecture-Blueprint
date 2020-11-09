package com.mureed.app.data.model

import androidx.room.*


@Entity
data class Channel(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var title: String? = "",
    @Embedded
    var iconAsset: IconAsset?,
    @Embedded
    var coverAsset: CoverAsset?
) {

    @Ignore
    var latestMedia: List<Media>? = listOf()

    @Ignore
    var series: List<Media>? = listOf()

}

data class IconAsset(
    var thumbnailUrl: String? = null
)

data class ChannelEntity(
    @Embedded
    var channel: Channel,

    @Relation(
        parentColumn = "id",
        entityColumn = "courseId"
    )
    var latestMedia: List<Media>? = listOf(),

    @Relation(
        parentColumn = "id",
        entityColumn = "seriesId"
    )
    var series: List<Media>? = listOf()

)