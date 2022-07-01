package com.example.coursematerial.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.coursematerial.BuildConfig
import com.example.coursematerial.R
import com.example.coursematerial.model.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response



class EpicViewModel(

    private val liveDataForViewToObserve: MutableLiveData<AppState> = MutableLiveData(),
    private val retrofitImpl: EpicRetrofitImpl = EpicRetrofitImpl()
) : ViewModel() {

    fun getLiveDataForViewToObserve() = liveDataForViewToObserve

    fun sendServerRequest() {
        val apiKey: String = BuildConfig.NASA_API_KEY
        if (apiKey.isBlank()) {
            liveDataForViewToObserve.value = AppState.Error(418)
        } else {
            retrofitImpl.getEpicImpl().getEPIC(apiKey).enqueue(callback)
        }
    }

    private val callback = object : Callback<EpicServerResponseData> {
        override fun onResponse(
            call: Call<EpicServerResponseData>,
            response: Response<EpicServerResponseData>
        ) {
            if (response.isSuccessful){
                response.body()?.let {
                    liveDataForViewToObserve.postValue(AppState.SuccessEpic(it))
                }
            }
        }

        override fun onFailure(call: Call<EpicServerResponseData>, t: Throwable) {
            liveDataForViewToObserve.postValue(AppState.Error(R.string.error_code))
        }

    }


}























   /* private val liveDataForViewToObserve: MutableLiveData<AppState> = MutableLiveData(),
    private val marsRetrofitImpl: MarsOfTheDayRetrofitImpl = MarsOfTheDayRetrofitImpl()
) : ViewModel() {

    fun getLiveDataForViewToObserve() = liveDataForViewToObserve

    fun sendServerRequest() {
        val apiKey: String = BuildConfig.NASA_API_KEY
        if (apiKey.isBlank()) {
            liveDataForViewToObserve.value = AppState.Error(418)
        } else {
            marsRetrofitImpl.getMarsRetrofitImpl().getMarsImageByDate(apiKey).enqueue(marsCallback)
        }
    }

   /* fun sendServerRequest(date:String) {
        liveDataForViewToObserve.value = AppState.Loading(0)
        val apiKey: String = BuildConfig.NASA_API_KEY
        if (apiKey.isBlank()) {
            liveDataForViewToObserve.value = AppState.Error(418)
        } else {
            marsRetrofitImpl.getMarsRetrofitImpl().getMarsImageByDate(apiKey).enqueue(marsCallback)
        }
    }*/



    private val marsCallback = object : Callback<PictureOfTheDayServerResponseData>{
        override fun onResponse(
            call: Call<PictureOfTheDayServerResponseData>,
            response: Response<PictureOfTheDayServerResponseData>
        ) {
            if(response.isSuccessful){
                response.body()?.let {
                    liveDataForViewToObserve.postValue(AppState.Success(it))
                }
            }else{
                liveDataForViewToObserve.postValue(AppState.Error(R.string.error_code))
            }
        }

        override fun onFailure(call: Call<PictureOfTheDayServerResponseData>, t: Throwable) {
            liveDataForViewToObserve.postValue(AppState.Error(R.string.error_code))
        }

    }


}*/