package ru.spectr.weatherapp.ui.components.forecast

import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil

data class ForecastItem(
    val id: Long,
    val payload: Any?,
    val temp: String,
    val tempMin: String,
    val date: String,
    val weatherType: WeatherType,
    val humidity: String,
    val pressure: String,
    val description: String,
    val windSpeed: String
) {
    companion object {
        private val DIFF = object : DiffUtil.ItemCallback<ForecastItem>() {
            override fun areItemsTheSame(oldItem: ForecastItem, newItem: ForecastItem) = oldItem.id == newItem.id
            override fun areContentsTheSame(oldItem: ForecastItem, newItem: ForecastItem) = oldItem == newItem
        }

        val config by lazy {
            AsyncDifferConfig
                .Builder(DIFF)
                .build()
        }
    }
}