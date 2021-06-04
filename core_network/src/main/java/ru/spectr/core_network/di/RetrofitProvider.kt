package ru.spectr.core_network.di

import com.google.gson.Gson
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import toothpick.InjectConstructor
import javax.inject.Provider

private const val BASE_URL = "https://www.metaweather.com/api/"

@InjectConstructor
class RetrofitProvider(
    private val okHttpClient: OkHttpClient,
    private val gson: Gson
) : Provider<Retrofit> {
    override fun get(): Retrofit = Retrofit
        .Builder()
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(okHttpClient)
        .baseUrl(BASE_URL)
        .build()
}