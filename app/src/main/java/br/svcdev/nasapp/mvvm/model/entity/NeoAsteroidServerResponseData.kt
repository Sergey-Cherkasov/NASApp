package br.svcdev.nasapp.mvvm.model.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class NeoAsteroidServerResponseData(
    @field:SerializedName("near_earth_objects") val objects: List<NearEarthObject>
) : Parcelable