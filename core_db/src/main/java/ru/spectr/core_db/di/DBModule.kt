package ru.spectr.core_db.di

import ru.spectr.core_db.AppDatabase
import toothpick.config.Module
import toothpick.ktp.binding.bind

class DBModule : Module() {
    init {
//        bind<AppDatabase>().toInstance(AppDatabase_Impl()) // Work
//        bind(AppDatabase::class.java).toInstance(AppDatabase_Impl()) // Work
//        bind(AppDatabase::class).toClass(AppDatabase_Impl::class) // NOT WORK
        bind<AppDatabase>().toProvider(DatabaseProvider::class).singleton()// Work
    }
}