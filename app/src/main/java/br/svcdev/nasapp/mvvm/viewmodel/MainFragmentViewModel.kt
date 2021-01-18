package br.svcdev.nasapp.mvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.svcdev.nasapp.BuildConfig
import br.svcdev.nasapp.mvvm.model.entity.NasaServerResponseData
import br.svcdev.nasapp.mvvm.model.entity.PictureOfTheDayData
import br.svcdev.nasapp.mvvm.model.repository.RetrofitImpl
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainFragmentViewModel : ViewModel() {

    private val liveData: MutableLiveData<PictureOfTheDayData> = MutableLiveData()
    private val liveDataShowProgress: MutableLiveData<Boolean> = MutableLiveData()
    private val retrofit = RetrofitImpl()

    fun getData(): LiveData<PictureOfTheDayData> {
        sendServerRequest()
        return liveData
    }

    fun getProgressState(): LiveData<Boolean> = liveDataShowProgress

    private fun sendServerRequest() {
        liveData.value = PictureOfTheDayData.Loading(null)
        val apiKey: String = BuildConfig.NASA_API_KEY
        if (apiKey.isBlank()) {
            PictureOfTheDayData.Error(Throwable("You need API key"))
        } else {
            liveDataShowProgress.value = true
            retrofit.getRetrofitImpl().getPictureOfTheDay(apiKey).enqueue(object :
                Callback<NasaServerResponseData> {
                override fun onResponse(
                    call: Call<NasaServerResponseData>,
                    response: Response<NasaServerResponseData>
                ) {
                    if (response.isSuccessful && response.body() != null) {
                        liveData.value =
                            PictureOfTheDayData.Success(response.body()!!)
                        liveDataShowProgress.value = false
                    } else {
                        val message = response.message()
                        if (message.isNullOrEmpty()) {
                            liveData.value =
                                PictureOfTheDayData.Error(Throwable("Unidentified error"))
                        } else {
                            liveData.value =
                                PictureOfTheDayData.Error(Throwable(message))
                        }
                    }
                }

                override fun onFailure(call: Call<NasaServerResponseData>, t: Throwable) {
                    liveData.value = PictureOfTheDayData.Error(t)
                }
            })
        }
    }

}