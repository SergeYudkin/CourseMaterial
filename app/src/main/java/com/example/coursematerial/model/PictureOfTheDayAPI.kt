package com.example.coursematerial.model

import com.example.coursematerial.BuildConfig
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PictureOfTheDayAPI {
    @GET("planetary/apod")
    fun getPictureOfTheDay(@Query("api_key") apiKey:String):Call<PictureOfTheDayServerResponseData>

    @GET("planetary/apod")
    fun getPictureOfTheDay(
        @Query("api_key") apiKey:String,
        @Query("date") date:String
    ):Call<PictureOfTheDayServerResponseData>



    // Earth Polychromatic Imaging Camera
    @GET("EPIC/api/natural")
    fun getEPIC(
        @Query("api_key") apiKey: String,
    ): Call<List<PictureOfTheDayServerResponseData>>

    @GET("/mars-photos/api/v1/rovers/curiosity/photos")
    fun getMarsImageByDate(
        @Query("earth_date") earth_date: String,
        @Query("api_key") apiKey: String,
    ): Call<PictureOfTheDayServerResponseData>


    @GET("DONKI/FLR")
    fun getSolarFlare(
        @Query("api_key") apiKey: String,
        @Query("startDate") startDate: String,
    ): Call<List<PictureOfTheDayServerResponseData>>

    @GET("/planetary/earth/assets")
    fun getLandscapeImageFromSputnik(
        @Query("lon") lon: Float,
        @Query("lat") lat: Float,
        @Query("date") dateString: String,
        @Query("dim") dim: Float,
        @Query("api_key") apiKey: String
    ): Call<PictureOfTheDayServerResponseData>
}
