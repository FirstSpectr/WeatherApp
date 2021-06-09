package ru.spectr.core_network

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ru.spectr.core_network.models.ConsolidatedWeather
import ru.spectr.core_network.models.ForecastResponse
import ru.spectr.core_network.models.LocationDTO

interface MetaWeatherApi {
    /**
     * Returns a distance-sorted list of the location next to the coordinates
     * @param latLng Coordinates to search for locations near. Comma separated lattitude and longitude e.g. "36.96,-122.02".
     * @return list of [LocationDTO] with distance field
     */
    @GET("location/search")
    suspend fun searchByCord(@Query("lattlong") latLng: String): List<LocationDTO>

    /**
     * Returns the locations matches to query
     * @param query Text to search for e.g. London
     * @return list of [LocationDTO]
     */
    @GET("location/search")
    suspend fun searchByName(@Query("query") query: String): List<LocationDTO>


    /**
     * Returns location information, and a 5 day forecast
     * @param woeid Where On Earth ID e.g. 44418
     * @return [ForecastResponse]
     */
    @GET("location/{woeid}")
    suspend fun getForecast(@Path("woeid") woeid: Int): ForecastResponse

    /**
     * Returns a date ordered list of fields as described in the consolidated_weather section of the location method
     * Returns the weather forecast for the day
     * @param woeid Where On Earth ID e.g. 44418
     * @param date Date in the format yyyy/mm/dd. Most location have data from early 2013 to 5-10 days in the future e.g. "2013/4/27"
     * @return list of [ConsolidatedWeather]
     */
    @GET("location/{woeid}/{date}")
    suspend fun getForecastForDay(@Path("woeid") woeid: Int, @Path("date") date: String): List<ConsolidatedWeather>
}