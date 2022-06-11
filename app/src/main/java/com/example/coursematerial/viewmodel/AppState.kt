package com.example.coursematerial.viewmodel

import com.example.coursematerial.model.PictureOfTheDayServerResponseData

sealed class AppState{

    data class Success(val serverResponseData: PictureOfTheDayServerResponseData) : AppState()
    data class Error(val error: Throwable) : AppState()
    data class Loading(val progress: Int?) : AppState()
}
