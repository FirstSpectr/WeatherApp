package ru.spectr.core_network.di

import retrofit2.Retrofit
import ru.spectr.core_network.metaweaher.MetaWeatherApi
import toothpick.InjectConstructor
import javax.inject.Provider

@InjectConstructor
class ApiProvider(
    private val retrofit: Retrofit
) : Provider<MetaWeatherApi> {
    override fun get(): MetaWeatherApi = retrofit.create(MetaWeatherApi::class.java)
}