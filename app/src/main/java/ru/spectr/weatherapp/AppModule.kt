package ru.spectr.weatherapp

import android.app.Application
import android.content.Context
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import ru.spectr.core.resources.ResourceProvider
import ru.spectr.core.resources.ResourceProviderImpl
import toothpick.config.Module
import toothpick.ktp.binding.bind

class AppModule(application: Application) : Module() {
    init {
        val cicerone = Cicerone.create(Router())
        bind<Router>().toInstance(cicerone.router)
        bind<NavigatorHolder>().toInstance(cicerone.getNavigatorHolder())

        bind<ResourceProvider>().toClass<ResourceProviderImpl>().singleton()
        bind<FusedLocationProviderClient>().toInstance(LocationServices.getFusedLocationProviderClient(application))
        bind<Context>().toInstance(application)
        bind<Application>().toInstance(application)
    }
}