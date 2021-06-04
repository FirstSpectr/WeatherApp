package ru.spectr.core_network.data

import ru.spectr.core_network.metaweaher.models.ForecastResponse
import ru.spectr.core_network.metaweaher.models.Location

interface Repository {
    suspend fun saveToFavorites(searchResponse: Location)
    suspend fun removeFromFavorites(woeid: Int)
    suspend fun getFavorites(): List<Location>

    suspend fun searchByCord(lattLong: String): List<Location>
    suspend fun searchByName(name: String): List<Location>
    suspend fun getData(woeid: Int): ForecastResponse
}