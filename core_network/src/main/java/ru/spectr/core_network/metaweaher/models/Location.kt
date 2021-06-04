package ru.spectr.core_network.metaweaher.models

data class Location(
    val title: String,
    val location_type: String,
    val woeid: Int,
    val latt_long: String
)