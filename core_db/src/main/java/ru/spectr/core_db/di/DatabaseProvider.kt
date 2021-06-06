package ru.spectr.core_db.di

import android.content.Context
import androidx.room.Room
import ru.spectr.core_db.AppDatabase
import toothpick.InjectConstructor
import javax.inject.Provider

private const val DB_NAME = "weather_app_db"

@InjectConstructor
class DatabaseProvider(
    private val context: Context
) : Provider<AppDatabase> {
    override fun get(): AppDatabase = Room.databaseBuilder(context, AppDatabase::class.java, DB_NAME).build()
}