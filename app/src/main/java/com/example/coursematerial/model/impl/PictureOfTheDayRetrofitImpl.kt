package com.example.coursematerial.model.impl

import com.example.coursematerial.model.apis.PictureOfTheDayAPI
import com.example.coursematerial.view.api.baseUrl
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val podRetrofit = Retrofit.Builder().baseUrl(baseUrl)
    .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
    .build()!!


class PictureOfTheDayRetrofitImpl {

    fun getRetrofitImpl(): PictureOfTheDayAPI {

        return podRetrofit.create(PictureOfTheDayAPI::class.java)
    }

}


