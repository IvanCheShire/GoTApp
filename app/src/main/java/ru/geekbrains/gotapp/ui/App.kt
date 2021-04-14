package ru.geekbrains.geekbrains_popular_libraries_kotlin.ui

import android.app.Application
import ru.geekbrains.geekbrains_popular_libraries_kotlin.di.AppComponent
import ru.geekbrains.geekbrains_popular_libraries_kotlin.di.modules.AppModule
import ru.terrakok.cicerone.Cicerone
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity.room.db.Database
import ru.geekbrains.gotapp.di.house.HouseSubcomponent
import ru.geekbrains.gotapp.di.house.IHouseScopeContainer
import ru.geekbrains.gotapp.di.repository.IRepositoryScopeContainer
import ru.geekbrains.gotapp.di.repository.RepositorySubcomponent

class App : Application(), IHouseScopeContainer, IRepositoryScopeContainer {
    companion object {
        lateinit var instance: App
    }

    lateinit var appComponent: AppComponent
        private set
    var houseSubcomponent: HouseSubcomponent? = null
        private set
    var repositorySubcomponent: RepositorySubcomponent? = null
        private set


    override fun onCreate() {
        super.onCreate()
        instance = this
        appComponent =  DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }
    fun initHouseComponent() = appComponent.houseSubcomponent().also {
        houseSubcomponent = it
    }

    fun initRepositoryComponent() = houseSubcomponent?.repositorySubcomponent().also {
        repositorySubcomponent = it
    }

    override fun releaseHouseScope() {
        houseSubcomponent = null
    }

    override fun releaseRepositoryScope() {
        repositorySubcomponent = null
    }
}
