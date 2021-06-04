package ru.spectr.weatherapp.ui.splash

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.spectr.core.installVMBinding
import ru.spectr.weatherapp.R
import ru.spectr.weatherapp.databinding.FragmentSplashBinding
import toothpick.ktp.KTP
import toothpick.ktp.delegate.inject
import toothpick.smoothie.viewmodel.closeOnViewModelCleared


class SplashFragment : Fragment(R.layout.fragment_splash) {
    private val viewModel: SplashViewModel by inject<SplashViewModelImpl>()
    private val viewBinding by viewBinding(FragmentSplashBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        KTP.openRootScope()
            .openSubScope(this)
            .installVMBinding<SplashViewModelImpl>(this)
            .closeOnViewModelCleared(this)
            .inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}