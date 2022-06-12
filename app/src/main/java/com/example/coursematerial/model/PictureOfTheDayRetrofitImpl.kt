package com.example.coursematerial.model

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val baseUrl = "https://api.nasa.gov/"
val podRetrofit = Retrofit.Builder().baseUrl(baseUrl)
    .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
    .build()!!


class PictureOfTheDayRetrofitImpl {

    fun getRetrofitImpl(): PictureOfTheDayAPI{

        return podRetrofit.create(PictureOfTheDayAPI::class.java)
    }
}