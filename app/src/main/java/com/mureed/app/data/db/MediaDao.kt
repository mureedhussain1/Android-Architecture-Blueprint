package com.mureed.app.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.mureed.app.data.model.Media


@Dao
interface MediaDao {

    @Insert
    fun insert(media: List<Media>)

    @Query("SELECT * FROM Media WHERE seriesId <= 0 or courseId <= 0 LIMIT 6")
    fun getNewEpisodes(): LiveData<List<Media>>

    @Query("DELETE FROM Media WHERE seriesId <= 0 or courseId <= 0")
    suspend fun deleteAllNewEpisodes()

    @Query("DELETE FROM Media WHERE seriesId > 0 or courseId > 0")
    suspend fun deleteAllChannelsMedia()

}