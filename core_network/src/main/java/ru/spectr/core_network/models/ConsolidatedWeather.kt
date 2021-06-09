package ru.spectr.core_network.models

import com.google.gson.annotations.SerializedName

const val APPLICABLE_DATE_PATTERN = "yyyy-MM-dd"

data class ConsolidatedWeather(
    @SerializedName("id")
    val id: Long?,
    @SerializedName("air_pressure")
    val airPressure: Float?,
    @SerializedName("applicable_date")
    val applicableDate: String?,
    @SerializedName("created")
    val created: String?,
    @SerializedName("humidity")
    val humidity: Int?,
    @SerializedName("max_temp")
    val maxTemp: Float?,
    @SerializedName("min_temp")
    val minTemp: Float?,
    @SerializedName("predictability")
    val predictability: Int?,
    @SerializedName("the_temp")
    val theTemp: Float?,
    @SerializedName("visibility")
    val visibility: Float?,
    @SerializedName("weather_state_abbr")
    val weatherStateAbbr: String?,
    @SerializedName("weather_state_name")
    val weatherStateName: String?,
    @SerializedName("wind_direction")
    val windDirection: Float?,
    @SerializedName("wind_direction_compass")
    val windDirectionCompass: String?,
    @SerializedName("wind_speed")
    val windSpeed: Float?
)