package ru.spectr.core_data.models

data class Forecast(
    val id: Long,
    val temp: Float,
    val date: Long,
    val weatherType: String,
    val tempMin: Float,
    val humidity: Int,
    val pressure: Float,
    val description: String,
    val windSpeed: Float
)