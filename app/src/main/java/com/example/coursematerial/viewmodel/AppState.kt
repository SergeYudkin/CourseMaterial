package com.example.coursematerial.viewmodel



import com.example.coursematerial.model.EarthEpicServerResponseData
import com.example.coursematerial.model.MarsPhotosServerResponseData
import com.example.coursematerial.model.MarsServerResponseData
import com.example.coursematerial.model.PictureOfTheDayServerResponseData

sealed class AppState{

    data class Success(val serverResponseData: PictureOfTheDayServerResponseData) : AppState()
    data class SuccessMars(val marsServerResponseData: MarsPhotosServerResponseData) : AppState()
    data class SuccessEpic(val epicServerResponseData: List <EarthEpicServerResponseData>) : AppState()
    data class Error(val error: Int) : AppState()
    data class Loading(val progress: Int?) : AppState()
}
