package ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.repo

import io.reactivex.rxjava3.core.Single
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity.HouseRepository
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity.House

interface IHouseReposRepo {
    fun getHouseRepos(house: House): Single<List<HouseRepository>>
}