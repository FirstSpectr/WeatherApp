package ru.spectr.weatherapp.ui.map

import com.google.android.gms.maps.model.LatLng

interface MapViewModel {
    fun onLocationSelected(location: LatLng)
}