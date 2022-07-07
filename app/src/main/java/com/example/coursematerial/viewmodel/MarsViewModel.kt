package com.example.coursematerial.viewmodel

import android.os.Build
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.coursematerial.BuildConfig
import com.example.coursematerial.R
import com.example.coursematerial.model.MarsPhotosServerResponseData
import com.example.coursematerial.model.impl.MarsRetrofitImpl
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*


class MarsViewModel(

    private val liveDataForViewToObserve: MutableLiveData<AppState> = MutableLiveData(),
    private val marRetrofitImpl: MarsRetrofitImpl = MarsRetrofitImpl()
) : ViewModel() {

    fun getLiveDataForViewToObserve() = liveDataForViewToObserve

    fun marsSendServerRequest() {
        val earthDate = getDayBeforeYesterday()
        marRetrofitImpl.getMarsPictureByDate(earthDate,BuildConfig.NASA_API_KEY, marsCallback)
    }

    private val marsCallback = object : Callback<MarsPhotosServerResponseData> {
        override fun onResponse(
            call: Call<MarsPhotosServerResponseData>,
            response: Response<MarsPhotosServerResponseData>
        ) {
            if (response.isSuccessful && response.body() !=null){
                liveDataForViewToObserve.postValue(AppState.SuccessMars(response.body()!!))

            }else{
                val message = response.message()
                if (message.isNullOrEmpty()){
                    liveDataForViewToObserve.postValue(AppState.Error(R.string.error_code))
                }else{
                    liveDataForViewToObserve.postValue(AppState.Error(R.string.error_code))
                }

            }
        }

        override fun onFailure(call: Call<MarsPhotosServerResponseData>, t: Throwable) {
            liveDataForViewToObserve.postValue(AppState.Error(R.string.error_code))
        }
    }

    private fun getDayBeforeYesterday(): String {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val yesterday = LocalDateTime.now().minusDays(2)
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
            return yesterday.format(formatter)
        } else {
            val cal: Calendar = Calendar.getInstance()
            val s = SimpleDateFormat("yyyy-MM-dd")
            cal.add(Calendar.DAY_OF_YEAR, -2)
            return s.format(cal.time)
        }
    }

}