package com.example.coursematerial.model

import com.google.gson.annotations.SerializedName

data class MarsPhotosServerResponseData(
    @field:SerializedName("photos") val photos: ArrayList<MarsServerResponseData>,
)