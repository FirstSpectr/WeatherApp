package ru.spectr.weatherapp.ui.day

import androidx.lifecycle.LiveData
import ru.spectr.weatherapp.ui.components.forecast.ForecastItem

interface DayViewModel {
    val items: LiveData<List<ForecastItem>>
    val title: LiveData<String>
    val isRefreshing: LiveData<Boolean>

    fun onRefresh()
    fun onBackPressed()
}