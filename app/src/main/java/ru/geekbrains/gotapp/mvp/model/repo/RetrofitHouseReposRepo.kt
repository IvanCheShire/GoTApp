package ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.repo

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.api.IDataSource
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.cache.IGithubUserReposCache
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.cache.IHouseReposCache
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity.GithubRepository
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity.GithubUser
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity.House
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity.HouseRepository
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity.room.RoomGithubRepository
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity.room.db.Database
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.network.INetworkStatus

class RetrofitHouseReposRepo(val api: IDataSource, val networkStatus: INetworkStatus, val cache: IHouseReposCache) : IHouseReposRepo {

    override fun getHouseRepos(house: House) = networkStatus.inOnlineSingle().flatMap { isOnline ->
        if (isOnline) {
            house.reposUrl?.let { url ->
                api.getHouseRepos(url).flatMap { repositories ->
                    cache.putHouseRepos(house, repositories).andThen(Single.just(repositories))
                }
            } ?: Single.error<List<HouseRepository>>(RuntimeException("House has no repos url")).subscribeOn(Schedulers.io())

        } else {
            cache.getHouseRepos(house)
        }
    }.subscribeOn(Schedulers.io())
}