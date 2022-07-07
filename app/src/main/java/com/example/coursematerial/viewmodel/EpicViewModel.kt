package com.example.coursematerial.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.coursematerial.BuildConfig
import com.example.coursematerial.R
import com.example.coursematerial.model.EarthEpicServerResponseData
import com.example.coursematerial.model.impl.EarthEpicRetrofitImpl
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class EpicViewModel(

    private val liveDataForViewToObserve: MutableLiveData<AppState> = MutableLiveData(),
    private val eRetrofitImpl: EarthEpicRetrofitImpl = EarthEpicRetrofitImpl()
) : ViewModel() {

    fun getLiveDataForViewToObserve() = liveDataForViewToObserve

    fun epicSendServerRequest() {
        val apiKey =  BuildConfig.NASA_API_KEY
        if (apiKey.isBlank()) {
            liveDataForViewToObserve.value = AppState.Error(418)
        } else {
            eRetrofitImpl.getEpicImpl(apiKey,epicCallback)
        }
    }

    private val epicCallback = object : Callback<List<EarthEpicServerResponseData>> {
        override fun onResponse(
            call: Call<List<EarthEpicServerResponseData>>,
            response: Response<List<EarthEpicServerResponseData>>
        ) {
            if (response.isSuccessful && response.body() !=null){
                liveDataForViewToObserve.postValue(AppState.SuccessEpic(response.body()!!))

                }else{
                    val message = response.message()
                if (message.isNullOrEmpty()){
                    liveDataForViewToObserve.postValue(AppState.Error(R.string.error_code))
                }else{
                    liveDataForViewToObserve.postValue(AppState.Error(R.string.error_code))
                }

                }
            }

        override fun onFailure(call: Call<List<EarthEpicServerResponseData>>, t: Throwable) {
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