package ru.spectr.core_network.metaweaher

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ru.spectr.core_network.metaweaher.models.ForecastResponse
import ru.spectr.core_network.metaweaher.models.Location

interface MetaWeatherApi {
    @GET("location/search")
    suspend fun searchByCord(@Query("lattlong") lattLong: String): List<Location>

    @GET("location/search")
    suspend fun searchByName(@Query("query") name: String): List<Location>

    @GET("location/{woeid}")
    suspend fun getData(@Path("woeid") woeid: Int): ForecastResponse
}