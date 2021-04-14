package ru.geekbrains.geekbrains_popular_libraries_kotlin.di.modules

import androidx.room.Room.*
import dagger.Module
import dagger.Provides
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.cache.IHouseReposCache
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.cache.IHousesCache
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity.room.db.Database
import ru.geekbrains.geekbrains_popular_libraries_kotlin.ui.App
import ru.geekbrains.geekbrains_popular_libraries_kotlin.ui.cache.RoomHouseReposCache
import ru.geekbrains.geekbrains_popular_libraries_kotlin.ui.cache.RoomHousesCache
import javax.inject.Singleton

@Module
class CacheModule {

    @Singleton
    @Provides
    fun database(app: App): Database = databaseBuilder(app, Database::class.java, Database.DB_NAME).build()

    @Singleton
    @Provides
    fun usersCache(database: Database): IHousesCache = RoomHousesCache(database)

    fun userReposCache(database: Database): IHouseReposCache = RoomHouseReposCache(database)



}

