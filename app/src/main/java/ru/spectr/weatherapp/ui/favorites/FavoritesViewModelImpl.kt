package ru.spectr.weatherapp.ui.favorites

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.terrakok.cicerone.Router
import kotlinx.coroutines.launch
import ru.spectr.core_data.Repo
import ru.spectr.core_data.models.Location
import ru.spectr.weatherapp.navigation.Screens.Favorites.FAVORITES_RESULT_KEY
import ru.spectr.weatherapp.ui.components.search_item.SearchItem
import timber.log.Timber
import toothpick.InjectConstructor

@InjectConstructor
class FavoritesViewModelImpl(
    private val router: Router,
    private val repo: Repo
) : ViewModel(), FavoritesViewModel {
    override val items = MutableLiveData<List<SearchItem>>()

    init {
        viewModelScope.launch {
            try {
                val favorites = repo.getFavoriteLocations()
                items.value = favorites.map { it.toSearchItem() }
            } catch (e: Exception) {
                Timber.e(e)
            }
        }
    }

    override fun onLocationSelected(item: SearchItem) {
        router.sendResult(FAVORITES_RESULT_KEY, item.id)
        router.exit()
    }

    override fun onFavIconClick(item: SearchItem) {
        viewModelScope.launch {
            try {
                repo.removeFromFavorites(item.id)
                val newList = items.value?.toMutableList() ?: return@launch
                newList.remove(item)
                items.value = newList
            } catch (e: Exception) {
                Timber.e(e)
            }
        }
    }

    override fun onBackPressed() = router.exit()

    private fun Location.toSearchItem() = SearchItem(
        id = woeid,
        title = title,
        locationType = locationType,
        isFavorite = isFavorite
    )
}