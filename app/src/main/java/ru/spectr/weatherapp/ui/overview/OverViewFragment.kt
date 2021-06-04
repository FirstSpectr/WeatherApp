package ru.spectr.weatherapp.ui.overview

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.maps.model.LatLng
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import ru.spectr.core.installVMBinding
import ru.spectr.weatherapp.BuildConfig
import ru.spectr.weatherapp.R
import ru.spectr.weatherapp.databinding.FragmentOverviewBinding
import ru.spectr.weatherapp.ui.components.forecast.ForecastItem
import ru.spectr.weatherapp.ui.components.forecast.forecastAdapterDelegate
import toothpick.ktp.KTP
import toothpick.ktp.delegate.inject
import toothpick.smoothie.viewmodel.closeOnViewModelCleared


class OverViewFragment : Fragment(R.layout.fragment_overview) {
    private val viewModel: OverViewModel by inject<OverviewModelImpl>()
    private val binding by viewBinding(FragmentOverviewBinding::bind)
    private val locationProviderClient by inject<FusedLocationProviderClient>()

    private val adapter by lazy {
        AsyncListDifferDelegationAdapter(
            ForecastItem.config,
            forecastAdapterDelegate()
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        KTP.openRootScope()
            .openSubScope(this)
            .installVMBinding<OverviewModelImpl>(this)
            .closeOnViewModelCleared(this)
            .inject(this)
        setHasOptionsMenu(true)
    }

    private val requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) {
        if (it) {
            locationProviderClient.lastLocation.addOnSuccessListener { location ->
                viewModel.onMyLocationClick(LatLng(location.latitude, location.longitude))
            }
        } else {
            Snackbar.make(binding.root, R.string.permission_message, BaseTransientBottomBar.LENGTH_LONG)
                .setAction(R.string.grant) { startActivity(getAppSettingsIntent()) }
                .show()
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            ivSearch.setOnClickListener { viewModel.onSearchClick() }
            ivLocate.setOnClickListener { viewModel.onLocateOnMapClick() }
            ivFavorite.setOnClickListener { viewModel.onFavoritesClick() }
            ivMyLocation.setOnClickListener {
                if (ContextCompat.checkSelfPermission(requireContext(), ACCESS_COARSE_LOCATION) == PERMISSION_GRANTED) {
                    locationProviderClient.lastLocation.addOnSuccessListener {
                        viewModel.onMyLocationClick(LatLng(it.latitude, it.longitude))
                    }
                } else {
                    requestPermissionLauncher.launch(ACCESS_COARSE_LOCATION)
                }
            }
            swipeRefresh.setOnRefreshListener { viewModel.onRefresh() }

            recycler.adapter = adapter

            recycler.addItemDecoration(DividerItemDecoration(recycler.context, (recycler.layoutManager as LinearLayoutManager).orientation))

            with(viewModel) {
                currentLocation.observe(viewLifecycleOwner, tvCurrentLocation::setText)
                isRefreshing.observe(viewLifecycleOwner, swipeRefresh::setRefreshing)
                progressVisible.observe(viewLifecycleOwner) { progressBar.isVisible = it }
                items.observe(viewLifecycleOwner) { adapter.items = it }
            }
        }
    }

    private fun getAppSettingsIntent(): Intent {
        val intent = Intent()
        intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
        val uri = Uri.fromParts("package", BuildConfig.APPLICATION_ID, null)
        intent.data = uri
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        return intent
    }
}