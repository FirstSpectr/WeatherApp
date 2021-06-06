package ru.spectr.core_network.models

data class ConsolidatedWeather(
    val id: Long?,
    val air_pressure: Float?,
    val applicable_date: String?,
    val created: String?,
    val humidity: Int?,
    val max_temp: Float?,
    val min_temp: Float?,
    val predictability: Int?,
    val the_temp: Float?,
    val visibility: Float?,
    val weather_state_abbr: String?,
    val weather_state_name: String?,
    val wind_direction: Float?,
    val wind_direction_compass: String?,
    val wind_speed: Float?
) {
    companion object {
        const val DATE_PATTERN = "yyyy-MM-dd"
    }
}