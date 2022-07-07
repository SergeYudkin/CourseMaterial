package com.example.coursematerial.model.impl

import com.example.coursematerial.model.MarsPhotosServerResponseData
import com.example.coursematerial.model.apis.PictureOfTheDayAPI
import com.example.coursematerial.view.api.baseUrl
import com.google.gson.GsonBuilder
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


private val marsRetrofit by lazy { Retrofit.Builder().baseUrl(baseUrl)
    .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
    .build().create(PictureOfTheDayAPI::class.java) }


class MarsRetrofitImpl {


    fun getMarsPictureByDate(earth_date: String, apiKey: String, marsCallbackByDate: Callback<MarsPhotosServerResponseData>) {
        marsRetrofit.getMarsImageByDate(earth_date, apiKey).enqueue(marsCallbackByDate)
    }
}


