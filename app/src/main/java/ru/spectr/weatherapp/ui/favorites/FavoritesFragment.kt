package ru.spectr.weatherapp.ui.favorites

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import ru.spectr.core.extensions.installVMBinding
import ru.spectr.weatherapp.R
import ru.spectr.weatherapp.databinding.FragmentFavoritesBinding
import ru.spectr.weatherapp.ui.components.search_item.SearchItem
import ru.spectr.weatherapp.ui.components.search_item.searchAdapterDelegate
import toothpick.ktp.KTP
import toothpick.ktp.delegate.inject
import toothpick.smoothie.viewmodel.closeOnViewModelCleared

class FavoritesFragment : Fragment(R.layout.fragment_favorites) {
    private val viewModel: FavoritesViewModel by inject<FavoritesViewModelImpl>()
    private val binding by viewBinding(FragmentFavoritesBinding::bind)

    private val adapter by lazy {
        AsyncListDifferDelegationAdapter(
            SearchItem.config,
            searchAdapterDelegate(onClick = viewModel::onLocationSelected, onFavClick = viewModel::onFavIconClick)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        KTP.openRootScope()
            .openSubScope(this)
            .installVMBinding<FavoritesViewModelImpl>(this)
            .closeOnViewModelCleared(this)
            .inject(this)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            recycler.adapter = adapter

            recycler.addItemDecoration(DividerItemDecoration(recycler.context, (recycler.layoutManager as LinearLayoutManager).orientation))
            toolbar.setNavigationOnClickListener { viewModel.onBackPressed() }
            with(viewModel) {
                items.observe(viewLifecycleOwner) {
                    adapter.items = it
                }
            }
        }
    }
}