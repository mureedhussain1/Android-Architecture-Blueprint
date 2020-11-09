package com.mureed.app.data.channels

import com.mureed.app.data.model.CategoryResponse
import com.mureed.app.data.model.ChannelResponse
import com.mureed.app.data.model.EpisodeResponse
import retrofit2.Response
import retrofit2.http.GET

interface ChannelRemoteDataSource {

    @GET("/raw/Xt12uVhM")
    suspend fun getNewEpisodes(): Response<EpisodeResponse>


    @GET("/raw/Xt12uVhM")
    suspend fun getChannels(): Response<ChannelResponse>


    @GET("/raw/Xt12uVhM")
    suspend fun getCategories(): Response<CategoryResponse>

}