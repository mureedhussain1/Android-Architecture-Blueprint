package com.mureed.app.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mureed.app.data.model.Category
import com.mureed.app.data.model.Channel
import com.mureed.app.data.model.Media

@Database(
    entities = [
        Category::class, Media::class, Channel::class
    ],
    version = 1,
    exportSchema = false
)
abstract class MyDatabase : RoomDatabase() {

    abstract fun categoryDao(): CategoryDao

    abstract fun channelDao(): ChannelDao

    abstract fun mediaDao(): MediaDao

    companion object {
        // Singleton prevents multiple instances of database opening at the same time.
        @Volatile
        private var INSTANCE: MyDatabase? = null

        const val DATABASE_NAME = "MyDatabase.db"

        fun getDatabase(context: Context): MyDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MyDatabase::class.java,
                    DATABASE_NAME
                )
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }

}