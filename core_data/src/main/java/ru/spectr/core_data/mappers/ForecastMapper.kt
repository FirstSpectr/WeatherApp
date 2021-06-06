package ru.spectr.core_data.mappers

import ru.spectr.core.extensions.toDate
import ru.spectr.core_data.models.Forecast
import ru.spectr.core_data.models.LocationInformation
import ru.spectr.core_network.models.ConsolidatedWeather
import ru.spectr.core_network.models.ForecastResponse

fun ConsolidatedWeather.toForecast(): Forecast = Forecast(
    id = id ?: 0L,
    temp = the_temp ?: 0f,
    date = applicable_date?.toDate(ConsolidatedWeather.DATE_PATTERN)?.time ?: 0L,
    weatherType = weather_state_abbr.orEmpty(),
    tempMin = min_temp ?: 0f,
    humidity = humidity ?: 0,
    pressure = air_pressure ?: 0f,
    description = weather_state_name.orEmpty(),
    windSpeed = wind_speed ?: 0f
)

fun ForecastResponse.toLocationInformation() = LocationInformation(
    woeid = woeid ?: 0,
    title = title.orEmpty(),
    forecasts = consolidated_weather?.map { it.toForecast() }.orEmpty(),
    time = time.orEmpty(),
    timezone = timezone.orEmpty()
)