package ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.cache

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity.House

interface IHousesCache {
    fun putHouses(houses: List<House>): Completable
    fun getHouses(): Single<List<House>>
}