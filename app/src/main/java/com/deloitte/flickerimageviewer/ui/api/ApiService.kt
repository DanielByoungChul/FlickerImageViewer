package com.deloitte.flickerimageviewer.ui.api

import com.deloitte.flickerimageviewer.ui.api.response.ResponsePhotos
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/services/rest")
    suspend fun getPhotos(
        @Query("method") method: String,
        @Query("api_key") apiKey: String,
        @Query("text") text: String,
        @Query("format") format: String,
        @Query("nojsoncallback") nojsoncallback: String
    ):ResponsePhotos

}