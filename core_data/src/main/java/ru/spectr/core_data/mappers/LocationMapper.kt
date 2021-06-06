package ru.spectr.core_data.mappers

import ru.spectr.core_data.models.Location
import ru.spectr.core_db.models.entitity.LocationDB
import ru.spectr.core_network.models.LocationDTO


fun LocationDTO.toLocationVo(isInFavorites: Boolean = false) = Location(
    woeid = woeid,
    title = title,
    locationType = location_type,
    lattLong = latt_long,
    isFavorite = isInFavorites
)

fun LocationDB.toLocationVo() = Location(
    woeid = woeid,
    title = title,
    locationType = locationType,
    lattLong = lattLong,
    isFavorite = true
)

fun Location.toDB() = LocationDB(
    woeid = woeid,
    title = title,
    locationType = locationType,
    lattLong = lattLong
)

