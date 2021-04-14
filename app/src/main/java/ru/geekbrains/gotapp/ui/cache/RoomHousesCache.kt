package ru.geekbrains.geekbrains_popular_libraries_kotlin.ui.cache

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.cache.IHousesCache
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity.House
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity.room.RoomHouse
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity.room.db.Database

class RoomHousesCache(val db: Database): IHousesCache {

    override fun putHouses(houses: List<House>): Completable {
        return Completable.fromCallable {
            val roomHouses = houses.map { house ->
                RoomHouse(
                    house.name ?: "",
                    house.region ?: "",
                    house.words ?: "",
                    house.currentLord ?: ""
                )
            }
            db.houseDao.insert(roomHouses)
            houses
        }
    }

    override fun getHouses(): Single<List<House>> {
        return Single.fromCallable {
            db.houseDao.getAll().map { roomHouse ->
                House(roomHouse.name, roomHouse.region, roomHouse.words, roomHouse.currentLord)
            }
        }
    }
}