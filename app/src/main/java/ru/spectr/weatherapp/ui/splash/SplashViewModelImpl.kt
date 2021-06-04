package ru.spectr.weatherapp.ui.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.terrakok.cicerone.Router
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.spectr.weatherapp.ui.Screens
import toothpick.InjectConstructor

@InjectConstructor
class SplashViewModelImpl(
    private val router: Router
) : ViewModel(), SplashViewModel {

    init {
        viewModelScope.launch {
            delay(3000)
            router.newRootScreen(Screens.Main)
        }
    }
}