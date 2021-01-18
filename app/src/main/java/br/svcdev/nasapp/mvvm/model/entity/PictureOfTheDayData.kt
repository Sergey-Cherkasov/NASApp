package br.svcdev.nasapp.mvvm.model.entity

sealed class PictureOfTheDayData {
    data class Success(val serverResponseData: NasaServerResponseData) : PictureOfTheDayData()
    data class Error(val error: Throwable) : PictureOfTheDayData()
    data class Loading(val progress: Int?) : PictureOfTheDayData()
}