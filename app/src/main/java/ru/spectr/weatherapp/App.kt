package ru.spectr.weatherapp

import android.app.Application
import ru.spectr.core_data.di.DataModule
import ru.spectr.core_db.di.DBModule
import ru.spectr.core_network.di.NetworkModule
import timber.log.Timber
import toothpick.Scope
import toothpick.ktp.KTP

class App : Application() {
    private lateinit var scope: Scope

    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)
        scope.release()
    }

    override fun onCreate() {
        super.onCreate()
        initDi()
        initLog()
    }

    private fun initDi() {
        scope = KTP.openScope(this).installModules(
            AppModule(this@App),
            DBModule(),
            NetworkModule(),
            DataModule()
        )
    }

    private fun initLog() {
        if (BuildConfig.DEBUG)
            Timber.plant(Timber.DebugTree())
    }
}