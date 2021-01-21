package br.svcdev.nasapp.mvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.svcdev.nasapp.BuildConfig
import androidx.lifecycle.ViewModel
import br.svcdev.nasapp.mvvm.model.entity.NeoAsteroidData
import br.svcdev.nasapp.mvvm.model.entity.NeoAsteroidServerResponseData
import br.svcdev.nasapp.mvvm.model.repository.RetrofitImpl
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback

class AsteroidsFragmentViewModel : ViewModel() {

    private val liveData: MutableLiveData<NeoAsteroidData> = MutableLiveData()
    private val retrofit = RetrofitImpl()

    fun getData(): LiveData<NeoAsteroidData> {
        sendServerRequest()
        return liveData
    }

    private fun sendServerRequest() {
        liveData.value = NeoAsteroidData.Loading(null)
        val apiKey: String = BuildConfig.NASA_API_KEY
        if (apiKey.isBlank()) {
            NeoAsteroidData.Error(Throwable("You need API key"))
        } else {
            retrofit.getRetrofitImpl().getListAsteroids(apiKey).enqueue(object :
                Callback<NeoAsteroidServerResponseData> {

                override fun onResponse(call: Call<NeoAsteroidServerResponseData>,
                    response: Response<NeoAsteroidServerResponseData>) {
                    if (response.isSuccessful && response.body() != null) {
                        liveData.value =NeoAsteroidData.Success(response.body()!!)
                    } else {
                        val message = response.message()
                        if (message.isNullOrEmpty()) {
                            liveData.value = NeoAsteroidData.Error(Throwable("Unidentified error"))
                        } else {
                            liveData.value = NeoAsteroidData.Error(Throwable(message))
                        }
                    }
                }

                override fun onFailure(call: Call<NeoAsteroidServerResponseData>, t: Throwable) {
                    liveData.value = NeoAsteroidData.Error(t)
                }

            })
        }
    }
}