package ru.spectr.core_data

import com.google.android.gms.maps.model.LatLng
import ru.spectr.core_data.mappers.toDB
import ru.spectr.core_data.mappers.toForecast
import ru.spectr.core_data.mappers.toLocationInformation
import ru.spectr.core_data.mappers.toLocationVo
import ru.spectr.core_data.models.Location
import ru.spectr.core_data.models.LocationInformation
import ru.spectr.core_db.AppDatabase
import ru.spectr.core_network.MetaWeatherApi
import toothpick.InjectConstructor

@InjectConstructor
class RepoImpl(
    private val weatherApi: MetaWeatherApi,
    private val weatherDb: AppDatabase
) : Repo {
    override suspend fun getFavoriteLocations(): List<Location> = weatherDb.locationDao().getAll().map { it.toLocationVo() }

    override suspend fun getLocationsByName(name: String): List<Location> {
        val favorites = weatherDb.locationDao().getAll()
        val locations = weatherApi.searchByName(name)
        return locations.map { location -> location.toLocationVo(favorites.find { location.woeid == it.woeid } != null) }
    }

    override suspend fun getLocationsByCord(latLng: LatLng): List<Location> {
        val favorites = weatherDb.locationDao().getAll()
        val locations = weatherApi.searchByCord(lattLong = "${latLng.latitude},${latLng.longitude}")
        return locations.map { location -> location.toLocationVo(favorites.find { location.woeid == it.woeid } != null) }
    }

    override suspend fun addToFavorites(locationVo: Location) = weatherDb.locationDao().insertAll(locationVo.toDB())

    override suspend fun removeFromFavorites(woeid: Int) = weatherDb.locationDao().delete(woeid)

    override suspend fun getForecast(woeid: Int): LocationInformation = weatherApi.getForecast(woeid).toLocationInformation()

    override suspend fun getForecastForDay(woeid: Int, date: String) = weatherApi.getForecastForDay(woeid, date).map { it.toForecast() }
}