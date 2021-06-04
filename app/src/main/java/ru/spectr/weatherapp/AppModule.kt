package ru.spectr.weatherapp

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import ru.spectr.core.resources.ResourceProvider
import ru.spectr.core.resources.ResourceProviderImpl
import ru.spectr.core_network.data.Repository
import ru.spectr.core_network.data.RepositoryImpl
import toothpick.config.Module
import toothpick.ktp.binding.bind

private const val SHARED_PREFERENCES_NAME = "SHARED_PREFERENCES_NAME"

class AppModule(application: Application) : Module() {
    init {
        val cicerone = Cicerone.create(Router())
        bind<Router>().toInstance(cicerone.router)
        bind<NavigatorHolder>().toInstance(cicerone.getNavigatorHolder())

        bind<ResourceProvider>().toClass<ResourceProviderImpl>().singleton()
        bind<FusedLocationProviderClient>().toInstance(LocationServices.getFusedLocationProviderClient(application))
        bind<Context>().toInstance(application)
        bind<Repository>().toClass<RepositoryImpl>()
        bind<SharedPreferences>().toInstance(application.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE))
    }
}