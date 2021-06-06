package ru.spectr.core_data

import com.google.android.gms.maps.model.LatLng
import ru.spectr.core_data.models.Forecast
import ru.spectr.core_data.models.Location
import ru.spectr.core_data.models.LocationInformation

interface Repo {
    suspend fun getFavoriteLocations(): List<Location>
    suspend fun getLocationsByName(name: String): List<Location>
    suspend fun getLocationsByCord(latLng: LatLng): List<Location>

    suspend fun addToFavorites(locationVo: Location)
    suspend fun removeFromFavorites(woeid: Int)

    suspend fun getForecast(woeid: Int): LocationInformation
    suspend fun getForecastForDay(woeid: Int, date: String): List<Forecast>
}