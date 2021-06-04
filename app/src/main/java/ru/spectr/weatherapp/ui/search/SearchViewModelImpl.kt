package ru.spectr.weatherapp.ui.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.terrakok.cicerone.Router
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import ru.spectr.core_network.data.Repository
import ru.spectr.core_network.metaweaher.MetaWeatherApi
import ru.spectr.core_network.metaweaher.models.Location
import ru.spectr.weatherapp.ui.Screens.Search.SEARCH_RESULT_KEY
import ru.spectr.weatherapp.ui.components.search_item.SearchItem
import timber.log.Timber
import toothpick.InjectConstructor

@InjectConstructor
class SearchViewModelImpl(
    private val router: Router,
    private val api: MetaWeatherApi,
    private val repository: Repository
) : ViewModel(), SearchViewModel {
    override val items = MutableLiveData<List<SearchItem>>()
    override val progressVisible = MutableLiveData(false)

    private var job: Job? = null

    override fun onQueryChanged(query: String) {
        progressVisible.value = true
        job?.cancel()
        job = viewModelScope.launch {
            try {
                val favorites = repository.getFavorites()
                val locations = api.searchByName(query)
                items.value = locations.map {
                    it.toSearchItem(favorites.find { favorite -> favorite.woeid == it.woeid } != null)
                }
            } catch (e: Exception) {
                items.value = listOf()
                Timber.e(e)
            }
            progressVisible.value = false
        }
    }

    override fun onLocationSelected(item: SearchItem) {
        router.sendResult(SEARCH_RESULT_KEY, item.id)
        router.exit()
    }

    override fun onFavIconClick(item: SearchItem) {
        viewModelScope.launch {
            try {
                if (item.isFavorite) {
                    repository.removeFromFavorites(item.id)
                } else {
                    repository.saveToFavorites(item.payload as Location)
                }
                val newList = items.value?.toMutableList() ?: return@launch
                val index = newList.indexOf(item)
                newList[index] = item.copy(isFavorite = !item.isFavorite)
                items.value = newList
            } catch (e: Exception) {
                Timber.e(e)
            }
        }
    }

    private fun Location.toSearchItem(isFavorite: Boolean) = SearchItem(
        id = woeid,
        payload = this,
        title = title,
        locationType = location_type,
        isFavorite = isFavorite
    )
}