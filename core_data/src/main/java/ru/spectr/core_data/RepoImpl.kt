package ru.spectr.core_data

import com.google.android.gms.maps.model.LatLng
import ru.spectr.core_data.mappers.toDB
import ru.spectr.core_data.mappers.toLocationVo
import ru.spectr.core_data.models.Location
import ru.spectr.core_db.AppDatabase
import ru.spectr.core_network.MetaWeatherApi
import ru.spectr.core_network.models.ForecastResponse
import timber.log.Timber
import toothpick.InjectConstructor

@InjectConstructor
class RepoImpl(
    private val weatherApi: MetaWeatherApi,
    private val weatherDb: AppDatabase
) : Repo {
    override suspend fun getFavoriteLocations(): List<Location> {
        return weatherDb.locationDao().getAll().map { it.toLocationVo() }
    }

    override suspend fun getLocationsByName(name: String): List<Location> {
        val favorites = weatherDb.locationDao().getAll()
//        Timber.d("favorites ${favorites}")

        val locations = weatherApi.searchByName(name)
        return locations.map { location -> location.toLocationVo(favorites.find { location.woeid == it.woeid } != null) }
    }

    override suspend fun getLocationsByCord(latLng: LatLng): List<Location> {
        val favorites = weatherDb.locationDao().getAll()
        val locations = weatherApi.searchByCord(lattLong = "${latLng.latitude},${latLng.longitude}")

        return locations.map { location -> location.toLocationVo(favorites.find { location.woeid == it.woeid } != null) }
    }

    override suspend fun addToFavorites(locationVo: Location) {
        Timber.d("BEFORE ADD ${weatherDb.locationDao().getAll()}")
        weatherDb.locationDao().insertAll(locationVo.toDB())
        Timber.d("AFTER ADD ${weatherDb.locationDao().getAll()}")
    }

    override suspend fun removeFromFavorites(woeid: Int) {
        Timber.d("BEFORE REMOVE ${weatherDb.locationDao().getAll()}")
        weatherDb.locationDao().delete(woeid)
        Timber.d("AFTER REMOVE ${weatherDb.locationDao().getAll()}")
    }

    override suspend fun getData(woeid: Int): ForecastResponse = weatherApi.getData(woeid)
}