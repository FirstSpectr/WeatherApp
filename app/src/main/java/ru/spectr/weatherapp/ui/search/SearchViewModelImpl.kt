package ru.spectr.weatherapp.ui.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.terrakok.cicerone.Router
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import ru.spectr.core_data.Repo
import ru.spectr.core_data.models.Location
import ru.spectr.weatherapp.navigation.Screens.Search.SEARCH_RESULT_KEY
import ru.spectr.weatherapp.ui.components.search_item.SearchItem
import timber.log.Timber
import toothpick.InjectConstructor

@InjectConstructor
class SearchViewModelImpl(
    private val router: Router,
    private val repo: Repo
) : ViewModel(), SearchViewModel {
    override val items = MutableLiveData<List<SearchItem>>()
    override val progressVisible = MutableLiveData(false)

    private var job: Job? = null

    override fun onQueryChanged(query: String) {
        progressVisible.value = true
        job?.cancel()
        job = viewModelScope.launch {
            try {
                items.value = repo.getLocationsByName(query).map { it.toSearchItem() }
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
                if (item.isFavorite) repo.removeFromFavorites(item.id)
                else repo.addToFavorites(item.payload as Location)

                val newList = items.value?.toMutableList() ?: return@launch
                val index = newList.indexOf(item)
                newList[index] = item.copy(isFavorite = !item.isFavorite)
                items.value = newList
            } catch (e: Exception) {
                Timber.e(e)
            }
        }
    }

    override fun onBackPressed() = router.exit()

    private fun Location.toSearchItem() = SearchItem(
        id = woeid,
        payload = this,
        title = title,
        locationType = locationType,
        isFavorite = isFavorite
    )
}