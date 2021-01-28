package br.svcdev.nasapp.mvvm.model.entity

sealed class NeoAsteroidData {
    data class Success(val serverResponseData: NeoAsteroidServerResponseData) : NeoAsteroidData()
    data class Error(val error: Throwable) : NeoAsteroidData()
    data class Loading(val progress: Int?) : NeoAsteroidData()
}