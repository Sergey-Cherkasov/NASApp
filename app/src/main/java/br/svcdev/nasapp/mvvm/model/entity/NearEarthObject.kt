package br.svcdev.nasapp.mvvm.model.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NearEarthObject(
    @field:SerializedName("name") val name: String
) : Parcelable