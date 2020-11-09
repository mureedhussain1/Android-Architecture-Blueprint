package com.mureed.app.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.mureed.app.data.model.Category

@Dao
interface CategoryDao {

    @Insert
    suspend fun insert(categories: List<Category>)

    @Query("SELECT * from Category")
    fun getCategories(): LiveData<List<Category>>

    @Query("DELETE FROM Category")
    suspend fun deleteAll()

}