package ru.spectr.core_network.models

data class LocationDTO(
    val title: String,
    val location_type: String,
    val woeid: Int,
    val latt_long: String
)