package ru.geekbrains.gotapp.di.repository.module

import dagger.Module
import dagger.Provides
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.api.IDataSource
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.cache.IHouseReposCache
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity.room.db.Database
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.network.INetworkStatus
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.repo.IHouseReposRepo
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.repo.RetrofitHouseReposRepo
import ru.geekbrains.geekbrains_popular_libraries_kotlin.ui.App
import ru.geekbrains.geekbrains_popular_libraries_kotlin.ui.cache.RoomHouseReposCache
import ru.geekbrains.gotapp.di.RepositoryScope
import ru.geekbrains.gotapp.di.repository.IRepositoryScopeContainer

@Module
class RepositoryModule {
    @RepositoryScope
    @Provides
    fun houseReposCache(database: Database): IHouseReposCache = RoomHouseReposCache(database)
    @RepositoryScope
    @Provides
    fun houseReposRepo(api: IDataSource, networkStatus: INetworkStatus, cache: IHouseReposCache): IHouseReposRepo = RetrofitHouseReposRepo(api, networkStatus, cache)

    @RepositoryScope
    @Provides
    fun scopeContainer(app: App): IRepositoryScopeContainer = app
}