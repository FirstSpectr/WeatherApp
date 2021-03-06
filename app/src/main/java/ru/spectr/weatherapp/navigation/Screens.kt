package ru.spectr.weatherapp.navigation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.spectr.weatherapp.ui.day.DayFragment
import ru.spectr.weatherapp.ui.favorites.FavoritesFragment
import ru.spectr.weatherapp.ui.map.MapFragment
import ru.spectr.weatherapp.ui.overview.OverViewFragment
import ru.spectr.weatherapp.ui.search.SearchFragment

object Screens {
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

    class Day(private val args: DayArguments) : FragmentScreen {
        override fun createFragment(factory: FragmentFactory): Fragment = DayFragment.newInstance(args)
    }
}