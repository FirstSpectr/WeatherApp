package ru.spectr.core_network.data

import ru.spectr.core_network.metaweaher.models.Location

interface Repository {
    suspend fun saveToFavorites(searchResponse: Location)
    suspend fun removeFromFavorites(woeid: Int)
    suspend fun getFavorites(): List<Location>
}