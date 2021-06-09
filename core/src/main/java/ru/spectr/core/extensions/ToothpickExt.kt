package ru.spectr.core.extensions

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import toothpick.Scope
import toothpick.smoothie.viewmodel.installViewModelBinding


inline fun <reified T : ViewModel> Scope.installVMBinding(fragment: Fragment): Scope {
    val providerFactory = object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return this@installVMBinding.getInstance(modelClass)
        }
    }
    installViewModelBinding<T>(fragment, providerFactory)
    return this
}

inline fun <reified T : ViewModel> Scope.installVMBinding(activity: AppCompatActivity): Scope {
    val providerFactory = object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return this@installVMBinding.getInstance(modelClass)
        }
    }
    installViewModelBinding<T>(activity, providerFactory)
    return this
}