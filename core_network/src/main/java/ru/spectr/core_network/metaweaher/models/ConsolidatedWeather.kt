package ru.spectr.core_network.metaweaher.models

data class ConsolidatedWeather(
    val id: Long?,
    val air_pressure: Float?,
    val applicable_date: String?,
    val created: String?,
    val humidity: Int?,
    val max_temp: Double?,
    val min_temp: Double?,
    val predictability: Int?,
    val the_temp: Float?,
    val visibility: Double?,
    val weather_state_abbr: String?,
    val weather_state_name: String?,
    val wind_direction: Double?,
    val wind_direction_compass: String?,
    val wind_speed: Double?
) {
    companion object {
        const val DATE_PATTERN = "yyyy-MM-dd"
    }
}