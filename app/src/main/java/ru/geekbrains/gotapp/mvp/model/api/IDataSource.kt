package ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.api

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity.HouseRepository
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity.House

interface IDataSource {

    @GET("houses")
    fun getHouses(): Single<List<House>>

    @GET("houses/{house}")
    fun getHouse(@Path("name") name: String): Single<House>

    @GET
    fun getHouseRepos(@Url url: String): Single<List<HouseRepository>>
}