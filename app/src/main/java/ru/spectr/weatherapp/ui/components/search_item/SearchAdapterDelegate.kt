package ru.spectr.weatherapp.ui.components.search_item

import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import ru.spectr.weatherapp.R
import ru.spectr.weatherapp.databinding.ItemSearchBinding

fun searchAdapterDelegate(onClick: (SearchItem) -> Unit = {}, onFavClick: (SearchItem) -> Unit = {}): AdapterDelegate<List<SearchItem>> {
    return adapterDelegateViewBinding(
        viewBinding = { layoutInflater, root ->
            ItemSearchBinding.inflate(layoutInflater, root, false)
        }
    ) {
        with(binding) {
            root.setOnClickListener { onClick(item) }
            ivFavorite.setOnClickListener { onFavClick(item) }

            bind {
                tvTitle.text = item.title
                tvLocationType.text = item.locationType

                val resId = if (item.isFavorite) R.drawable.ic_baseline_favorite_24 else R.drawable.ic_baseline_favorite_border_24
                ivFavorite.setImageResource(resId)
            }
        }
    }
}