package ru.geekbrains.gotapp.di.house.module

import dagger.Module
import dagger.Provides
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.api.IDataSource
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.cache.IHousesCache
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity.room.db.Database
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.network.INetworkStatus
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.repo.IHousesRepo
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.repo.RetrofitHousesRepo
import ru.geekbrains.geekbrains_popular_libraries_kotlin.ui.App
import ru.geekbrains.geekbrains_popular_libraries_kotlin.ui.cache.RoomHousesCache
import ru.geekbrains.gotapp.di.house.IHouseScopeContainer

@Module
class HouseModule {
    @HouseScope
    @Provides
    fun housesCache(database: Database): IHousesCache = RoomHousesCache(database)
    @HouseScope
    @Provides
    fun usersRepo(api: IDataSource, cache: IHousesCache, networkStatus: INetworkStatus): IHousesRepo = RetrofitHousesRepo(api, networkStatus, cache)

    @HouseScope
    @Provides
    fun scopeContainer(app: App): IHouseScopeContainer = app
}