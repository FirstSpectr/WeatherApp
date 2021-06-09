package ru.spectr.core_network.models

import com.google.gson.annotations.SerializedName

data class ConsolidatedWeather(
    @SerializedName("id")
    val id: Long?,
    @SerializedName("air_pressure")
    val air_pressure: Float?,
    @SerializedName("applicable_date")
    val applicable_date: String?,
    @SerializedName("created")
    val created: String?,
    @SerializedName("humidity")
    val humidity: Int?,
    @SerializedName("max_temp")
    val max_temp: Float?,
    @SerializedName("min_temp")
    val min_temp: Float?,
    @SerializedName("predictability")
    val predictability: Int?,
    @SerializedName("the_temp")
    val the_temp: Float?,
    @SerializedName("visibility")
    val visibility: Float?,
    @SerializedName("weather_state_abbr")
    val weather_state_abbr: String?,
    @SerializedName("weather_state_name")
    val weather_state_name: String?,
    @SerializedName("wind_direction")
    val wind_direction: Float?,
    @SerializedName("wind_direction_compass")
    val wind_direction_compass: String?,
    @SerializedName("wind_speed")
    val wind_speed: Float?
) {
    companion object {
        const val DATE_PATTERN = "yyyy-MM-dd"
    }
}