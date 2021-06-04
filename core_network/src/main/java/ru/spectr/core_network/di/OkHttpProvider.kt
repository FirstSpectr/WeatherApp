package ru.spectr.core_network.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import toothpick.InjectConstructor
import javax.inject.Provider

private val httpLogLevel = HttpLoggingInterceptor.Level.BODY

@InjectConstructor
class OkHttpProvider(
    private val context: Context
) : Provider<OkHttpClient> {
    override fun get(): OkHttpClient = OkHttpClient
        .Builder()
        .addInterceptor(ChuckerInterceptor.Builder(context).build())
        .addInterceptor(HttpLoggingInterceptor().apply { level = httpLogLevel })
        .build()
}