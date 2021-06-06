package ru.spectr.weatherapp.ui.day

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.terrakok.cicerone.Router
import kotlinx.coroutines.launch
import ru.spectr.core.extensions.FORMAT_dd_MMMM
import ru.spectr.core.extensions.format
import ru.spectr.core.resources.ResourceProvider
import ru.spectr.core_data.Repo
import ru.spectr.core_data.models.Forecast
import ru.spectr.weatherapp.R
import ru.spectr.weatherapp.navigation.DayArguments
import ru.spectr.weatherapp.ui.components.forecast.ForecastItem
import ru.spectr.weatherapp.ui.components.forecast.WeatherType
import timber.log.Timber
import toothpick.InjectConstructor
import kotlin.math.roundToInt

@InjectConstructor
class DayViewModelImpl(
    private val repo: Repo,
    private val router: Router,
    private val dayArguments: DayArguments,
    private val rp: ResourceProvider
) : ViewModel(), DayViewModel {
    override val title = MutableLiveData(dayArguments.title)
    override val items = MutableLiveData<List<ForecastItem>>()
    override val isRefreshing = MutableLiveData(true)

    init {
        loadForecast()
    }

    private fun loadForecast() {
        isRefreshing.value = true
        viewModelScope.launch {
            try {
                val data = repo.getForecastForDay(dayArguments.woeid, dayArguments.date)
                items.value = data.map { it.toForecastItem() }
            } catch (e: Exception) {
                Timber.e(e)
            }
            isRefreshing.value = false
        }
    }

    override fun onRefresh() = loadForecast()

    override fun onBackPressed() = router.exit()

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