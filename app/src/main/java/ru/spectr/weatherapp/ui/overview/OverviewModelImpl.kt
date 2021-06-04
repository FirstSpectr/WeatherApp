package ru.spectr.weatherapp.ui.overview

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.terrakok.cicerone.Router
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.launch
import ru.spectr.core.extensions.FORMAT_dd_MMMM
import ru.spectr.core.extensions.format
import ru.spectr.core.extensions.toDate
import ru.spectr.core.resources.ResourceProvider
import ru.spectr.core_network.data.Repository
import ru.spectr.core_network.metaweaher.models.ConsolidatedWeather
import ru.spectr.weatherapp.R
import ru.spectr.weatherapp.ui.Screens
import ru.spectr.weatherapp.ui.components.forecast.ForecastItem
import ru.spectr.weatherapp.ui.components.forecast.WeatherType
import timber.log.Timber
import toothpick.InjectConstructor
import java.util.*
import kotlin.math.roundToInt

@InjectConstructor
class OverviewModelImpl(
    private val router: Router,
    private val repository: Repository,
    private val rp: ResourceProvider
) : ViewModel(), OverViewModel {
    override val currentLocation = MutableLiveData<String>()
    override val items = MutableLiveData<List<ForecastItem>>()
    override val progressVisible = MutableLiveData(true)
    override val isRefreshing = MutableLiveData(true)

    private var lastWoeid = 2122265

    init {
        loadForecasts(2122265)
    }

    private fun loadForecasts(woeid: Int) {
        lastWoeid = woeid
        progressVisible.value = true
        viewModelScope.launch {
            try {
                val data = repository.getData(woeid)
                currentLocation.value = data.title
                items.value = data.consolidated_weather?.map { it.toForecast() }.orEmpty()
                progressVisible.value = false
            } catch (e: Exception) {
                Timber.e(e)
            }
            isRefreshing.value = false
        }
    }

    override fun onRefresh() {
        loadForecasts(lastWoeid)
    }

    override fun onLocateOnMapClick() {
        router.navigateTo(Screens.Map)
        router.setResultListener(Screens.Map.MAP_RESULT_KEY) {
            if (it !is LatLng) return@setResultListener
            viewModelScope.launch {
                try {
                    val nearLocation = repository.searchByCord("${it.latitude},${it.longitude}").firstOrNull() ?: return@launch
                    loadForecasts(nearLocation.woeid)
                } catch (e: Exception) {
                    Timber.e(e)
                }
            }
        }
    }

    override fun onSearchClick() {
        router.setResultListener(Screens.Search.SEARCH_RESULT_KEY) {
            if (it !is Int) return@setResultListener
            loadForecasts(woeid = it)
        }
        router.navigateTo(Screens.Search)
    }

    override fun onFavoritesClick() {
        router.setResultListener(Screens.Favorites.FAVORITES_RESULT_KEY) {
            if (it !is Int) return@setResultListener
            loadForecasts(woeid = it)
        }
        router.navigateTo(Screens.Favorites)
    }

    override fun onMyLocationClick(lng: LatLng) {
        viewModelScope.launch {
            try {
                val nearLocation = repository.searchByCord("${lng.latitude},${lng.longitude}").firstOrNull() ?: return@launch
                loadForecasts(nearLocation.woeid)
            } catch (e: Exception) {
                Timber.e(e)
            }
        }
    }

    private fun ConsolidatedWeather.toForecast() = ForecastItem(
        id = id ?: 0L,
        temp = "${the_temp?.roundToInt()}℃",
        date = applicable_date?.toDate(ConsolidatedWeather.DATE_PATTERN)?.format(FORMAT_dd_MMMM).orEmpty(),
        weatherType = WeatherType.find(weather_state_abbr),
        tempMin = "${min_temp?.roundToInt()}℃",
        humidity = rp.getString(R.string.humidity_info, humidity ?: 0),
        pressure = rp.getString(R.string.pressure_info, air_pressure?.roundToInt() ?: 0),
        description = weather_state_name.orEmpty(),
        windSpeed = rp.getString(R.string.wind_info, wind_speed ?: 0),
    )
}