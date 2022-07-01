package com.example.coursematerial.model


import com.google.gson.annotations.SerializedName

data class PictureOfTheDayServerResponseData(
    @SerializedName("copyright")
    val copyright: String,
    @SerializedName("date")
    val date: String,
    @SerializedName("explanation")
    val explanation: String,
    @SerializedName("hdurl")
    val hdurl: String,
    @SerializedName("media_type")
    val mediaType: String,
    @SerializedName("service_version")
    val serviceVersion: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("url")
    val url: String
)


data class MarsServerResponseData(
    @SerializedName("img_src")
    val imgSrc: String?,
    @SerializedName("earth_date")
    val earth_date: String?,
)
