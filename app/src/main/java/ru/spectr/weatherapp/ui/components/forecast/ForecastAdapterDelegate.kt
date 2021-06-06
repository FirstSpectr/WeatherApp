package ru.spectr.weatherapp.ui.components.forecast

import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import ru.spectr.weatherapp.databinding.ItemForecastBinding

fun forecastAdapterDelegate(onClick: ((ForecastItem) -> Unit)? = null): AdapterDelegate<List<ForecastItem>> {
    return adapterDelegateViewBinding(
        viewBinding = { layoutInflater, root ->
            ItemForecastBinding.inflate(layoutInflater, root, false)
        }
    ) {
        with(binding) {
            onClick?.let {
                binding.root.setOnClickListener { it(item) }
            }

            bind {
                tvDate.text = item.date
                tvTemperature.text = item.temp
                ivWeatherIcon.setImageResource(item.weatherType.iconResId)
                tvTempMin.text = item.tempMin
                tvHumidity.text = item.humidity
                tvPressure.text = item.pressure
                tvDescription.setText(item.weatherType.descResId)
                tvWindSpeed.text = item.windSpeed
            }
        }
    }
}