package com.mureed.app.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.mureed.app.data.model.Channel
import com.mureed.app.data.model.ChannelEntity

@Dao
interface ChannelDao {

    @Insert
    suspend fun insert(channels: Channel): Long

    @Transaction
    @Query("SELECT * FROM Channel")
    fun getChannels(): LiveData<List<ChannelEntity>>

    @Query("DELETE FROM Channel")
    suspend fun deleteAll()

}