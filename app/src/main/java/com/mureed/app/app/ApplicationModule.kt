package com.mureed.app.app

import android.content.Context
import com.mureed.app.data.channels.ChannelRemoteDataSource
import com.mureed.app.data.db.MyDatabase
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class ApplicationModule {

    @Singleton
    @Provides
    fun provideChannelRemoteDataSource(retrofit: Retrofit): ChannelRemoteDataSource =
        retrofit.create(ChannelRemoteDataSource::class.java)


    @Singleton
    @Provides
    fun provideMyDatabase(context: Context) =
        MyDatabase.getDatabase(context)

    @Singleton
    @Provides
    fun provideCategoryDao(database: MyDatabase) =
        database.categoryDao()

    @Singleton
    @Provides
    fun provideChannelDao(database: MyDatabase) =
        database.channelDao()

    @Singleton
    @Provides
    fun provideMediaDao(database: MyDatabase) =
        database.mediaDao()

}