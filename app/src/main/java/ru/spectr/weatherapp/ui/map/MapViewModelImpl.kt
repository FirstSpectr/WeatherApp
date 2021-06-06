package ru.spectr.weatherapp.ui.map

import androidx.lifecycle.ViewModel
import com.github.terrakok.cicerone.Router
import com.google.android.gms.maps.model.LatLng
import ru.spectr.weatherapp.navigation.Screens.Map.MAP_RESULT_KEY
import toothpick.InjectConstructor

@InjectConstructor
class MapViewModelImpl(
    private val router: Router
) : ViewModel(), MapViewModel {
    override fun onLocationSelected(location: LatLng) {
        router.sendResult(MAP_RESULT_KEY, location)
        router.exit()
    }
}