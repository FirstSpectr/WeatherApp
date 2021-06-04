package ru.spectr.core_network.data

import android.content.SharedPreferences
import com.google.gson.Gson
import ru.spectr.core_network.metaweaher.MetaWeatherApi
import ru.spectr.core_network.metaweaher.models.Location
import toothpick.InjectConstructor

private const val FAVORITE_KEY = "FAVORITE_KEY"

@InjectConstructor
class RepositoryImpl(private val api: MetaWeatherApi, private val sharedPreferences: SharedPreferences) : Repository {
    override suspend fun saveToFavorites(searchResponse: Location) {
        val favoritesJson = sharedPreferences.getString(FAVORITE_KEY, "")
        val list =
            if (favoritesJson?.isEmpty() == true) mutableListOf() else Gson().fromJson(favoritesJson, Array<Location>::class.java).toMutableList()
        list.add(searchResponse)
        sharedPreferences.edit().putString(FAVORITE_KEY, Gson().toJson(list)).apply()
    }

    override suspend fun removeFromFavorites(woeid: Int) {
        val favoritesJson = sharedPreferences.getString(FAVORITE_KEY, "")
        val list = Gson().fromJson(favoritesJson, Array<Location>::class.java).toMutableList()
        val location = list.firstOrNull { it.woeid == woeid } ?: return
        list.remove(location)
        sharedPreferences.edit().putString(FAVORITE_KEY, Gson().toJson(list)).apply()
    }

    override suspend fun getFavorites(): List<Location> {
        val favoritesJson = sharedPreferences.getString(FAVORITE_KEY, "")
        if (favoritesJson?.isEmpty() == true) return listOf()
        return Gson().fromJson(favoritesJson, Array<Location>::class.java).toList()
    }
}