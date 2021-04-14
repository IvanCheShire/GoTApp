package ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.navigation

import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity.HouseRepository
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity.House
import ru.geekbrains.geekbrains_popular_libraries_kotlin.ui.fragment.RepositoryFragment
import ru.geekbrains.geekbrains_popular_libraries_kotlin.ui.fragment.HouseFragment
import ru.geekbrains.geekbrains_popular_libraries_kotlin.ui.fragment.HousesFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class Screens {
    class UsersScreen() : SupportAppScreen() {
        override fun getFragment() = HousesFragment.newInstance()
    }
    class UserScreen(house: House): SupportAppScreen() {
        val house = house
        override fun getFragment() = HouseFragment.newInstance(house = house)
    }
    class RepositoryScreen(val house: HouseRepository) : SupportAppScreen() {
        override fun getFragment() = RepositoryFragment.newInstance(house)
    }
}
