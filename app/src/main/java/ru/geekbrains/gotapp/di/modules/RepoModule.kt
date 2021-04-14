package ru.geekbrains.geekbrains_popular_libraries_kotlin.di.modules

import dagger.Module
import dagger.Provides
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.api.IDataSource
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.cache.IHouseReposCache
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.cache.IHousesCache
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.network.INetworkStatus
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.repo.IHouseReposRepo
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.repo.IHousesRepo
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.repo.RetrofitHouseReposRepo
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.repo.RetrofitHousesRepo
import javax.inject.Singleton

@Module
class RepoModule {

    @Singleton
    @Provides
    fun usersRepo(api: IDataSource, cache: IHousesCache, networkStatus: INetworkStatus): IHousesRepo = RetrofitHousesRepo(api, networkStatus, cache)

    @Singleton
    @Provides
    fun userReposRepo(api: IDataSource, networkStatus: INetworkStatus, cache: IHouseReposCache): IHouseReposRepo = RetrofitHouseReposRepo(api, networkStatus, cache)
}