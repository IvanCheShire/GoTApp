package ru.geekbrains.geekbrains_popular_libraries_kotlin.di

import dagger.Component
import ru.geekbrains.geekbrains_popular_libraries_kotlin.di.modules.*
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.presenter.MainPresenter
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.presenter.RepositoryPresenter
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.presenter.HousePresenter
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.presenter.HousesPresenter
import ru.geekbrains.geekbrains_popular_libraries_kotlin.ui.activity.MainActivity
import ru.geekbrains.geekbrains_popular_libraries_kotlin.ui.adapter.HousesRVAdapter
import javax.inject.Singleton


@Singleton
@Component(modules = [
    AppModule::class,
    ApiModule::class,
    NavigationModule::class,
    CacheModule::class,
    RepoModule::class
])
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(mainPresenter: MainPresenter)
    fun inject(housesPresenter: HousesPresenter)
    fun inject(currentHousePresenter: HousePresenter)
    fun inject(repositoryPresenter: RepositoryPresenter)
    fun inject(housesRvAdapter: HousesRVAdapter)
}