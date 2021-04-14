package ru.geekbrains.geekbrains_popular_libraries_kotlin.ui.navigation

import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity.House
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.navigation.IScreens
import ru.geekbrains.geekbrains_popular_libraries_kotlin.ui.fragment.HouseFragment
import ru.geekbrains.geekbrains_popular_libraries_kotlin.ui.fragment.HousesFragment

class AndroidScreens : IScreens {
    override fun houses() = FragmentScreen { HousesFragment.newInstance() }
    override fun house(house: House) = FragmentScreen { HouseFragment.newInstance(house) }
}