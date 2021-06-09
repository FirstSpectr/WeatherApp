package ru.spectr.core_network.models

import com.google.gson.annotations.SerializedName

data class LocationDTO(
    @SerializedName("title")
    val title: String,
    @SerializedName("location_type")
    val locationType: String,
    @SerializedName("woeid")
    val woeid: Int,
    @SerializedName("latt_long")
    val lattLong: String
)