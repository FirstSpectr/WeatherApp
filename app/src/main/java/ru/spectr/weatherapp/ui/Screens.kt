package ru.spectr.weatherapp.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.spectr.weatherapp.ui.favorites.FavoritesFragment
import ru.spectr.weatherapp.ui.map.MapFragment
import ru.spectr.weatherapp.ui.overview.OverViewFragment
import ru.spectr.weatherapp.ui.search.SearchFragment
import ru.spectr.weatherapp.ui.splash.SplashFragment

object Screens {
    object Splash : FragmentScreen {
        override fun createFragment(factory: FragmentFactory): Fragment = SplashFragment()
    }

    object Main : FragmentScreen {
        override fun createFragment(factory: FragmentFactory): Fragment = OverViewFragment()
    }

    object Map : FragmentScreen {
        const val MAP_RESULT_KEY = "MAP_RESULT_KEY"
        override fun createFragment(factory: FragmentFactory): Fragment = MapFragment()
    }

    object Search : FragmentScreen {
        const val SEARCH_RESULT_KEY = "SEARCH_RESULT_KEY"
        override fun createFragment(factory: FragmentFactory): Fragment = SearchFragment()
    }

    object Favorites : FragmentScreen {
        const val FAVORITES_RESULT_KEY = "FAVORITES_RESULT_KEY"
        override fun createFragment(factory: FragmentFactory): Fragment = FavoritesFragment()
    }
}