package ru.spectr.core_network.di

import com.google.gson.Gson
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import ru.spectr.core_network.metaweaher.MetaWeatherApi
import toothpick.config.Module
import toothpick.ktp.binding.bind

class NetworkModule : Module() {
    init {
        bind(Gson::class.java)
            .toInstance(Gson())

        bind<OkHttpClient>()
            .toProvider(OkHttpProvider::class)
            .providesSingleton()

        bind<Retrofit>()
            .toProvider(RetrofitProvider::class)
            .providesSingleton()

        bind<MetaWeatherApi>().toProvider(ApiProvider::class)
            .providesSingleton()
    }
}