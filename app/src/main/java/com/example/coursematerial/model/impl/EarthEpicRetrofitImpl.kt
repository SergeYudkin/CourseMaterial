package com.example.coursematerial.model.impl

import com.example.coursematerial.model.EarthEpicServerResponseData
import com.example.coursematerial.model.apis.PictureOfTheDayAPI
import com.example.coursematerial.view.api.baseUrl
import com.google.gson.GsonBuilder
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


private val epicRetrofit by lazy { Retrofit.Builder().baseUrl(baseUrl)
    .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
    .build().create(PictureOfTheDayAPI::class.java) }


class EarthEpicRetrofitImpl {


    fun getEpicImpl(apiKey: String, epicCallback: Callback<List<EarthEpicServerResponseData>>) {
        epicRetrofit.getEPIC(apiKey).enqueue(epicCallback)
    }
}


