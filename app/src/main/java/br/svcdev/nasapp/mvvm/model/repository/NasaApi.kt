package br.svcdev.nasapp.mvvm.model.repository

import br.svcdev.nasapp.mvvm.model.entity.NasaServerResponseData
import br.svcdev.nasapp.mvvm.model.entity.NeoAsteroidServerResponseData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NasaApi {
    @GET("planetary/apod")
    fun getPictureOfTheDay(@Query("api_key") apiKey: String): Call<NasaServerResponseData>

    @GET("neo/rest/v1/neo/browse")
    fun getListAsteroids(@Query("api_key") apiKey: String): Call<NeoAsteroidServerResponseData>
}