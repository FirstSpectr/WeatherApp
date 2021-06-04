package ru.spectr.weatherapp.ui.components.forecast

import ru.spectr.weatherapp.R

enum class WeatherType(val resId: Int) {
    SN(R.drawable.ic_sn),
    SL(R.drawable.ic_sl),
    H(R.drawable.ic_h),
    T(R.drawable.ic_t),
    HR(R.drawable.ic_hr),
    LR(R.drawable.ic_lr),
    S(R.drawable.ic_sl),
    HC(R.drawable.ic_hc),
    LC(R.drawable.ic_lc),
    C(R.drawable.ic_c),
    UNKNOWN(R.drawable.ic_baseline_cloud_off_24);

    companion object {
        fun find(value: String?): WeatherType {
            for (weather in values()) {
                if (weather.toString().equals(value, true))
                    return weather
            }
            return UNKNOWN
        }
    }
}