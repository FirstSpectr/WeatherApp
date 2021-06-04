package ru.spectr.weatherapp.ui.search

import androidx.lifecycle.LiveData
import ru.spectr.weatherapp.ui.components.search_item.SearchItem

interface SearchViewModel {
    val items: LiveData<List<SearchItem>>
    val progressVisible: LiveData<Boolean>

    fun onQueryChanged(query: String)
    fun onLocationSelected(item: SearchItem)
    fun onFavIconClick(item: SearchItem)
}