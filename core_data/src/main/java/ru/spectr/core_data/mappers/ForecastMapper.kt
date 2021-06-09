package ru.spectr.core_data.mappers

import ru.spectr.core.extensions.toTime
import ru.spectr.core_data.models.Forecast
import ru.spectr.core_data.models.LocationInformation
import ru.spectr.core_network.models.APPLICABLE_DATE_PATTERN
import ru.spectr.core_network.models.ConsolidatedWeather
import ru.spectr.core_network.models.ForecastResponse

fun ConsolidatedWeather.toForecast(): Forecast = Forecast(
    id = id ?: 0L,
    temp = theTemp ?: 0f,
    date = applicableDate?.toTime(APPLICABLE_DATE_PATTERN) ?: 0L,
    weatherType = weatherStateAbbr.orEmpty(),
    tempMin = minTemp ?: 0f,
    humidity = humidity ?: 0,
    pressure = airPressure ?: 0f,
    description = weatherStateName.orEmpty(),
    windSpeed = windSpeed ?: 0f
)

fun ForecastResponse.toLocationInformation() = LocationInformation(
    woeid = woeid ?: 0,
    title = title.orEmpty(),
    forecasts = consolidatedWeather?.map { it.toForecast() }.orEmpty(),
    time = time.orEmpty(),
    timezone = timezone.orEmpty()
)