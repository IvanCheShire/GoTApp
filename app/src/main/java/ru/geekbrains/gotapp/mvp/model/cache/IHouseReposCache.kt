package ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.cache

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity.HouseRepository
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity.House

interface IHouseReposCache {
    fun putHouseRepos (user: House, repositories: List<HouseRepository>): Completable
    fun getHouseRepos (user: House): Single<List<HouseRepository>>
}