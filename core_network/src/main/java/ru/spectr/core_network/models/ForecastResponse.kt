package ru.spectr.core_network.models

import com.google.gson.annotations.SerializedName

data class ForecastResponse(
    @SerializedName("consolidated_weather")
    val consolidated_weather: List<ConsolidatedWeather>?,
    @SerializedName("latt_long")
    val latt_long: String?,
    @SerializedName("location_type")
    val location_type: String?,
    @SerializedName("parent")
    val parent: LocationDTO?,
    @SerializedName("sources")
    val sources: List<Source>?,
    @SerializedName("sun_rise")
    val sun_rise: String?,
    @SerializedName("sun_set")
    val sun_set: String?,
    @SerializedName("time")
    val time: String?,
    @SerializedName("timezone")
    val timezone: String?,
    @SerializedName("timezone_name")
    val timezone_name: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("woeid")
    val woeid: Int?
)