package ru.spectr.weatherapp.ui.components.forecast

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import ru.spectr.weatherapp.R

enum class WeatherType(@DrawableRes val iconResId: Int, @StringRes val descResId: Int) {
    SN(R.drawable.ic_sn, R.string.sn),
    SL(R.drawable.ic_sl, R.string.sl),
    H(R.drawable.ic_h, R.string.h),
    T(R.drawable.ic_t, R.string.t),
    HR(R.drawable.ic_hr, R.string.hr),
    LR(R.drawable.ic_lr, R.string.lr),
    S(R.drawable.ic_sl, R.string.sl),
    HC(R.drawable.ic_hc, R.string.hc),
    LC(R.drawable.ic_lc, R.string.lc),
    C(R.drawable.ic_c, R.string.c),
    UNKNOWN(R.drawable.ic_baseline_cloud_off_24, R.string.unknown);

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