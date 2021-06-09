package ru.spectr.core_network.models

import com.google.gson.annotations.SerializedName

data class ForecastResponse(
    @SerializedName("consolidated_weather")
    val consolidatedWeather: List<ConsolidatedWeather>?,
    @SerializedName("latt_long")
    val lattLong: String?,
    @SerializedName("location_type")
    val locationType: String?,
    @SerializedName("parent")
    val parent: LocationDTO?,
    @SerializedName("sources")
    val sources: List<Source>?,
    @SerializedName("sun_rise")
    val sunRise: String?,
    @SerializedName("sun_set")
    val sunSet: String?,
    @SerializedName("time")
    val time: String?,
    @SerializedName("timezone")
    val timezone: String?,
    @SerializedName("timezone_name")
    val timezoneName: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("woeid")
    val woeid: Int?
)