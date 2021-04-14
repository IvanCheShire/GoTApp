package ru.geekbrains.gotapp.di.house

import dagger.Subcomponent
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.presenter.HousesPresenter
import ru.geekbrains.geekbrains_popular_libraries_kotlin.ui.adapter.HousesRVAdapter
import ru.geekbrains.gotapp.di.house.module.HouseModule

@HouseScope
@Subcomponent(modules = [HouseModule::class])
interface HouseSubcomponent {
    fun repositorySubcomponent(): RepositorySubcomponent

    fun inject(housesPresenter: HousesPresenter)
    fun inject(housesRvAdapter: HousesRVAdapter)
}