package ru.spectr.weatherapp.ui.favorites

import androidx.lifecycle.LiveData
import ru.spectr.weatherapp.ui.components.search_item.SearchItem

interface FavoritesViewModel {
    val items: LiveData<List<SearchItem>>

    fun onLocationSelected(item: SearchItem)
    fun onFavIconClick(item: SearchItem)
    fun onBackPressed()
}