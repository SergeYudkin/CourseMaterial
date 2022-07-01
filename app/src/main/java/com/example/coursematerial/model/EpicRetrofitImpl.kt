package com.example.coursematerial.model

import com.example.coursematerial.view.api.baseUrl
import com.google.gson.GsonBuilder
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create


val epicRetrofit = Retrofit.Builder().baseUrl(baseUrl)
    .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
    .build()!!


class EpicRetrofitImpl {

    fun getEpicImpl(): EpicAPI {
        return epicRetrofit.create(EpicAPI::class.java)
    }
}


