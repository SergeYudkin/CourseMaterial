package com.example.coursematerial.model

import com.example.coursematerial.BuildConfig
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PictureOfTheDayAPI {
    @GET("/planetary/apod")
    fun getPictureOfTheDay(@Query("api_key")apiKey:String): Call<PictureOfTheDayServerResponseData>

    @GET("/planetary/apod")
    fun getPictureOfTheDayTemp(@Query("api_key") apiKey:String,@Query("date") date: String= "2022-06-11"): Call<PictureOfTheDayServerResponseData>
}