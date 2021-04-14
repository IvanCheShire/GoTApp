package ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.repo

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.api.IDataSource
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.cache.IHousesCache
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.network.INetworkStatus

class RetrofitHousesRepo(val api: IDataSource, val networkStatus: INetworkStatus, val cache: IHousesCache) : IHousesRepo {

    override fun getHouses() = networkStatus.inOnlineSingle().flatMap { isOnline ->
        if (isOnline) {
            api.getHouses().flatMap { houses ->
                cache.putHouses(houses).andThen(Single.just(houses))
            }
        } else {
            cache.getHouses()
        }
    }.subscribeOn(Schedulers.io())

}