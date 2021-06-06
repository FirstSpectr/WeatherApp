package ru.spectr.core_data.models

data class LocationInformation(
    val woeid: Int,
    val title: String,
    val forecasts: List<Forecast>,
    val time: String,
    val timezone: String
)