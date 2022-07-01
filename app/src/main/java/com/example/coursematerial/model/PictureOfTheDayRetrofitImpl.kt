package com.example.coursematerial.model

import com.example.coursematerial.view.api.baseUrl
import com.google.gson.GsonBuilder
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create


val podRetrofit = Retrofit.Builder().baseUrl(baseUrl)
    .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
    .build()!!


class PictureOfTheDayRetrofitImpl {

    fun getRetrofitImpl(): PictureOfTheDayAPI{

        return podRetrofit.create(PictureOfTheDayAPI::class.java)
    }


    fun getEpicImpl(): PictureOfTheDayAPI{
        return podRetrofit.create(PictureOfTheDayAPI::class.java)
    }
}


/*class MarsOfTheDayRetrofitImpl {

    fun getMarsRetrofitImpl(): PictureOfTheDayAPI {

        return podRetrofit.create(PictureOfTheDayAPI::class.java)
    }

}

class EPICRetrofitImpl{
    fun getEpicImpl(): PictureOfTheDayAPI{
        return podRetrofit.create(PictureOfTheDayAPI::class.java)
    }
}*/