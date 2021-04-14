package ru.geekbrains.gotapp.di.repository

import dagger.Subcomponent
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.presenter.HousePresenter
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.presenter.RepositoryPresenter
import ru.geekbrains.gotapp.di.RepositoryScope
import ru.geekbrains.gotapp.di.repository.module.RepositoryModule

@RepositoryScope
@Subcomponent( modules = [RepositoryModule::class])

interface RepositorySubcomponent {
    fun inject(housePresenter: HousePresenter)
    fun inject(repositoryPresenter: RepositoryPresenter)
}