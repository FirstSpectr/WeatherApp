package ru.spectr.core_network.models

data class ForecastResponse(
    val consolidated_weather: List<ConsolidatedWeather>?,
    val latt_long: String?,
    val location_type: String?,
    val parent: Parent?,
    val sources: List<Source>?,
    val sun_rise: String?,
    val sun_set: String?,
    val time: String?,
    val timezone: String?,
    val timezone_name: String?,
    val title: String?,
    val woeid: Int?
)