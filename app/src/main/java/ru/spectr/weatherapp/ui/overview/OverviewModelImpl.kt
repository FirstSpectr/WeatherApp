package ru.spectr.weatherapp.ui.overview

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.terrakok.cicerone.Router
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.launch
import ru.spectr.core.extensions.FORMAT_dd_MMMM
import ru.spectr.core.extensions.FORMAT_yyyy_MM_dd
import ru.spectr.core.extensions.format
import ru.spectr.core.resources.ResourceProvider
import ru.spectr.core_data.Repo
import ru.spectr.core_data.models.Forecast
import ru.spectr.weatherapp.R
import ru.spectr.weatherapp.navigation.DayArguments
import ru.spectr.weatherapp.navigation.Screens
import ru.spectr.weatherapp.ui.components.forecast.ForecastItem
import ru.spectr.weatherapp.ui.components.forecast.WeatherType
import timber.log.Timber
import toothpick.InjectConstructor
import java.util.*
import kotlin.math.roundToInt

@InjectConstructor
class OverviewModelImpl(
    private val router: Router,
    private val rp: ResourceProvider,
    private val repo: Repo
) : ViewModel(), OverViewModel {
    override val currentLocation = MutableLiveData<String>()
    override val items = MutableLiveData<List<ForecastItem>>()
    override val isRefreshing = MutableLiveData(true)
    override val progressVisible = MutableLiveData(true)

    private var lastWoeid = 2122265
//    private var lastLocationInformation:

    init {
        loadForecasts(2122265)
    }

    private fun loadForecasts(woeid: Int) {
        lastWoeid = woeid
        progressVisible.value = true
        viewModelScope.launch {
            try {
                val data = repo.getForecast(woeid)
                currentLocation.value = data.title
                items.value = data.forecasts.map { it.toForecastItem() }
                progressVisible.value = false
            } catch (e: Exception) {
                Timber.e(e)
            }
            isRefreshing.value = false
        }
    }

    override fun onRefresh() = loadForecasts(lastWoeid)

    override fun onLocateOnMapClick() {
        router.navigateTo(Screens.Map)
        router.setResultListener(Screens.Map.MAP_RESULT_KEY) {
            if (it !is LatLng) return@setResultListener
            viewModelScope.launch {
                try {
                    val nearLocation = repo.getLocationsByCord(it).firstOrNull() ?: return@launch
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
                val nearLocation = repo.getLocationsByCord(lng).firstOrNull() ?: return@launch
                loadForecasts(nearLocation.woeid)
            } catch (e: Exception) {
                Timber.e(e)
            }
        }
    }

    override fun onForecastClick(item: ForecastItem) {
        val forecast = item.payload as Forecast
        router.navigateTo(Screens.Day(DayArguments(lastWoeid, forecast.date.format(FORMAT_yyyy_MM_dd), currentLocation.value.orEmpty())))
    }

    private fun Forecast.toForecastItem() = ForecastItem(
        id = id,
        payload = this,
        temp = "${temp.roundToInt()}℃",
        date = date.format(FORMAT_dd_MMMM),
        weatherType = WeatherType.find(weatherType),
        tempMin = "${tempMin.roundToInt()}℃",
        humidity = rp.getString(R.string.humidity_info, humidity),
        pressure = rp.getString(R.string.pressure_info, pressure.roundToInt()),
        description = description,
        windSpeed = rp.getString(R.string.wind_info, windSpeed),
    )
}