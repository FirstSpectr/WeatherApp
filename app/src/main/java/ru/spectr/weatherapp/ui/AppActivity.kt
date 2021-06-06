package ru.spectr.weatherapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import ru.spectr.weatherapp.R
import ru.spectr.weatherapp.navigation.Screens
import toothpick.ktp.KTP
import toothpick.ktp.delegate.inject

class AppActivity : AppCompatActivity(R.layout.activity_app) {
    private val router by inject<Router>()
    private val navigatorHolder by inject<NavigatorHolder>()
    private val navigator = AppNavigator(activity = this, containerId = R.id.fragmentContainer)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        KTP.openRootScope().inject(this)

        if (savedInstanceState == null) {
            router.newRootScreen(Screens.Main)
        }
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator = navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }
}