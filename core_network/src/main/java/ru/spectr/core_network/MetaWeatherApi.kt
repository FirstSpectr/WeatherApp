package ru.spectr.core_network

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ru.spectr.core_network.models.ForecastResponse
import ru.spectr.core_network.models.LocationDTO

interface MetaWeatherApi {
    @GET("location/search")
    suspend fun searchByCord(@Query("lattlong") lattLong: String): List<LocationDTO>

    @GET("location/search")
    suspend fun searchByName(@Query("query") name: String): List<LocationDTO>

    @GET("location/{woeid}")
    suspend fun getData(@Path("woeid") woeid: Int): ForecastResponse
}