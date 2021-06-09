package ru.spectr.weatherapp.ui.day

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import ru.spectr.core.extensions.installVMBinding
import ru.spectr.weatherapp.R
import ru.spectr.weatherapp.databinding.FragmentDayBinding
import ru.spectr.weatherapp.navigation.DayArguments
import ru.spectr.weatherapp.ui.components.forecast.ForecastItem
import ru.spectr.weatherapp.ui.components.forecast.forecastAdapterDelegate
import toothpick.config.Module
import toothpick.ktp.KTP
import toothpick.ktp.binding.bind
import toothpick.ktp.delegate.inject
import toothpick.smoothie.viewmodel.closeOnViewModelCleared

class DayFragment : Fragment(R.layout.fragment_day) {
    private val viewModel: DayViewModel by inject<DayViewModelImpl>()
    private val binding by viewBinding(FragmentDayBinding::bind)

    private val adapter by lazy(mode = LazyThreadSafetyMode.NONE) {
        AsyncListDifferDelegationAdapter(
            ForecastItem.config,
            forecastAdapterDelegate()
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        KTP.openRootScope()
            .openSubScope(this)
            .installModules(object : Module() {
                init {
                    bind<DayArguments>().toInstance { requireArguments().getParcelable(ARGUMENTS_KEY)!! }
                }
            })
            .installVMBinding<DayViewModelImpl>(this)
            .closeOnViewModelCleared(this)
            .inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            swipeRefresh.setOnRefreshListener { viewModel.onRefresh() }

            recycler.adapter = adapter
            recycler.addItemDecoration(DividerItemDecoration(recycler.context, (recycler.layoutManager as LinearLayoutManager).orientation))

            toolbar.setNavigationOnClickListener { viewModel.onBackPressed() }

            with(viewModel) {
                isRefreshing.observe(viewLifecycleOwner, swipeRefresh::setRefreshing)
                items.observe(viewLifecycleOwner) { adapter.items = it }
                title.observe(viewLifecycleOwner, toolbar::setTitle)
            }
        }
    }

    companion object {
        private const val ARGUMENTS_KEY = "ARGUMENTS_KEY"

        fun newInstance(args: DayArguments) = DayFragment().apply {
            arguments = Bundle(1).apply {
                putParcelable(ARGUMENTS_KEY, args)
            }
        }
    }
}