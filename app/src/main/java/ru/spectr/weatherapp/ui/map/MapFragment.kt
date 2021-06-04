package ru.spectr.weatherapp.ui.map

import android.Manifest
import android.app.AlertDialog
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.widget.RelativeLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.MarkerOptions
import ru.spectr.core.installVMBinding
import ru.spectr.weatherapp.R
import ru.spectr.weatherapp.databinding.FragmentMapBinding
import toothpick.ktp.KTP
import toothpick.ktp.delegate.inject
import toothpick.smoothie.viewmodel.closeOnViewModelCleared


class MapFragment : Fragment(R.layout.fragment_map), OnMapReadyCallback {
    private val viewModel: MapViewModel by inject<MapViewModelImpl>()
    private val binding by viewBinding(FragmentMapBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        KTP.openRootScope()
            .openSubScope(this)
            .installVMBinding<MapViewModelImpl>(this)
            .closeOnViewModelCleared(this)
            .inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (childFragmentManager.findFragmentById(R.id.container) as SupportMapFragment).getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED)
            googleMap.isMyLocationEnabled = true

        val mapView = (childFragmentManager.findFragmentById(R.id.container) as SupportMapFragment).requireView()
        val locationButton = (mapView.findViewById<View>(1).parent as View).findViewById<View>(2)
        val rlp = locationButton.layoutParams as RelativeLayout.LayoutParams
        rlp.addRule(RelativeLayout.ALIGN_PARENT_TOP, 0)
        rlp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE)
        rlp.setMargins(0, 0, 0, 180)

        googleMap.setOnMapClickListener {
            val markerOptions = MarkerOptions()
            markerOptions.position(it)
            googleMap.clear()
            googleMap.addMarker(markerOptions)
            val builder = AlertDialog.Builder(requireContext())
            builder.setMessage(getString(R.string.map_alert_message))
            builder.setPositiveButton(getString(R.string.yes)) { _, _ -> viewModel.onLocationSelected(it) }
            builder.setNegativeButton(getString(R.string.no), null)
            val dialog: AlertDialog = builder.create()
            dialog.show()
        }
    }
}