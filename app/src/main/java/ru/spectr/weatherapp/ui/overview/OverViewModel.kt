package ru.spectr.weatherapp.ui.overview

import androidx.lifecycle.LiveData
import com.google.android.gms.maps.model.LatLng
import ru.spectr.weatherapp.ui.components.forecast.ForecastItem

interface OverViewModel {
    val items: LiveData<List<ForecastItem>>
    val currentLocation: LiveData<String>
    val progressVisible: LiveData<Boolean>
    val isRefreshing: LiveData<Boolean>

    fun onRefresh()
    fun onLocateOnMapClick()
    fun onSearchClick()
    fun onFavoritesClick()
    fun onMyLocationClick(lng: LatLng)
    fun onForecastClick(item: ForecastItem)
}