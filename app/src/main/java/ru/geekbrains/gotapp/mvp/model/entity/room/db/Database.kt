package ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity.room.db

import androidx.room.RoomDatabase
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity.room.RoomHouseRepository
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity.room.RoomHouse
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity.room.dao.RepositoryDao
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity.room.dao.HouseDao

@androidx.room.Database(entities = [RoomHouse::class, RoomHouseRepository::class], version = 3)
abstract class Database : RoomDatabase() {
    abstract val houseDao: HouseDao
    abstract val repositoryDao: RepositoryDao


    companion object {
        const val DB_NAME = "database.db"
        private var instance: Database? = null

    }

}