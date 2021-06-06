package ru.spectr.core_data.models

data class Location(
    val woeid: Int,
    val title: String,
    val locationType: String,
    val lattLong: String,
    val isFavorite: Boolean
)