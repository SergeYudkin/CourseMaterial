package com.example.coursematerial.model


import com.google.gson.annotations.SerializedName


data class EpicServerResponseData(
    @SerializedName("identifier")
    val identifier: String,
    @SerializedName("caption")
    val caption: String,
    @SerializedName("image")
    val image: String,
    val version: String,
    val date: String,
)