package ru.spectr.weatherapp.ui.components.search_item

import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil

data class SearchItem(
    val id: Int = -1,
    val payload: Any? = null,
    val title: String = "",
    val locationType: String = "",
    val isFavorite: Boolean = false
) {
    companion object {
        private val DIFF = object : DiffUtil.ItemCallback<SearchItem>() {
            override fun areItemsTheSame(oldItem: SearchItem, newItem: SearchItem) = oldItem.id == newItem.id
            override fun areContentsTheSame(oldItem: SearchItem, newItem: SearchItem) = oldItem == newItem
        }

        val config by lazy {
            AsyncDifferConfig
                .Builder(DIFF)
                .build()
        }
    }
}