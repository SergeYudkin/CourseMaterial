package com.example.coursematerial.viewmodel

import com.example.coursematerial.model.EpicServerResponseData
import com.example.coursematerial.model.MarsServerResponseData
import com.example.coursematerial.model.PictureOfTheDayServerResponseData

sealed class AppState{

    data class Success(val serverResponseData: PictureOfTheDayServerResponseData) : AppState()
    data class SuccessMars(val marsServerResponseData: PictureOfTheDayServerResponseData) : AppState()
    data class SuccessEpic(val epicServerResponseData: EpicServerResponseData) : AppState()
    data class Error(val error: Int) : AppState()
    data class Loading(val progress: Int?) : AppState()
}
