package ru.spectr.weatherapp.ui.search

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import ru.spectr.core.installVMBinding
import ru.spectr.weatherapp.R
import ru.spectr.weatherapp.databinding.FragmentSearchBinding
import ru.spectr.weatherapp.ui.components.search_item.SearchItem
import ru.spectr.weatherapp.ui.components.search_item.searchAdapterDelegate
import toothpick.ktp.KTP
import toothpick.ktp.delegate.inject
import toothpick.smoothie.viewmodel.closeOnViewModelCleared

class SearchFragment : Fragment(R.layout.fragment_search) {
    private val viewModel: SearchViewModel by inject<SearchViewModelImpl>()
    private val binding by viewBinding(FragmentSearchBinding::bind)

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
            .installVMBinding<SearchViewModelImpl>(this)
            .closeOnViewModelCleared(this)
            .inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            recycler.adapter = adapter
            val dividerItemDecoration = DividerItemDecoration(recycler.context, (recycler.layoutManager as LinearLayoutManager).orientation)
            recycler.addItemDecoration(dividerItemDecoration)

            etSearch.addTextChangedListener { viewModel.onQueryChanged(it.toString()) }
            toolbar.setNavigationOnClickListener { viewModel.onBackPressed() }
            with(viewModel) {
                progressVisible.observe(viewLifecycleOwner) { progressBar.isVisible = it }

                items.observe(viewLifecycleOwner) {
                    tvNothingFound.isVisible = it.isEmpty()
                    adapter.items = it
                }
            }
        }
    }
}