package ru.geekbrains.geekbrains_popular_libraries_kotlin.ui.cache

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.cache.IHouseReposCache
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity.HouseRepository
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity.House
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity.room.RoomHouseRepository
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity.room.db.Database

class RoomHouseReposCache(val db: Database): IHouseReposCache {

    override fun putHouseRepos(house: House, repositories: List<HouseRepository>): Completable {
        return Completable.fromCallable {
            val roomHouse = house.name?.let { db.houseDao.findByName(it) } ?: throw java.lang.RuntimeException("No such users in database")
            val roomRepos = repositories.map {
                RoomHouseRepository(

                    it.name ?: "",
                    it.forksCount ?: "",
                    it.htmlUrl ?: "",
                )
            }
            db.repositoryDao.insert(roomRepos)
            repositories
        }
    }

    override fun getHouseRepos(house: House): Single<List<HouseRepository>> {
        return Single.fromCallable {
            val roomHouse = house.name?.let { db.houseDao.findByName(it) } ?: throw java.lang.RuntimeException("No such houses in database")
            db.repositoryDao.findForHouse(roomHouse.name).map { HouseRepository(it.name, it.forksCount, it.htmlUrl) }
        }
    }

}