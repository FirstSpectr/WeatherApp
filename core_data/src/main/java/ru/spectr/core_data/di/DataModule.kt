package ru.spectr.core_data.di

import ru.spectr.core_data.Repo
import ru.spectr.core_data.RepoImpl
import toothpick.config.Module
import toothpick.ktp.binding.bind

class DataModule : Module() {
    init {
        bind<Repo>().toClass<RepoImpl>()
    }
}