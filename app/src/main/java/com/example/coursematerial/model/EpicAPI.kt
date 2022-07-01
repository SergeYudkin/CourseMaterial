package com.example.coursematerial.model

import com.example.coursematerial.BuildConfig
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface EpicAPI {


    // Earth Polychromatic Imaging Camera
    @GET("EPIC/api/natural")
    fun getEPIC(
        @Query("api_key") apiKey: String,
    ): Call<EpicServerResponseData>

}





