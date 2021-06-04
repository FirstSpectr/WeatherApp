package ru.spectr.weatherapp.ui.components.forecast

import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import ru.spectr.weatherapp.databinding.ItemForecastBinding

fun forecastAdapterDelegate(): AdapterDelegate<List<ForecastItem>> {
    return adapterDelegateViewBinding(
        viewBinding = { layoutInflater, root ->
            ItemForecastBinding.inflate(layoutInflater, root, false)
        }
    ) {
        with(binding) {
            bind {
                tvDate.text = item.date
                tvTemperature.text = item.temp
                ivWeatherIcon.setImageResource(item.weatherType.resId)
                tvTempMin.text = item.tempMin
                tvHumidity.text = item.humidity
                tvPressure.text = item.pressure
                tvDescription.text = item.description
                tvWindSpeed.text = item.windSpeed
            }
        }
    }
}