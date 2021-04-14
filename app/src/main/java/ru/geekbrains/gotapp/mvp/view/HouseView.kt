package ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.view

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface HouseView : MvpView {
    fun setNameToToolbar(houseName: String?)
    fun removeNameFromToolbar()
    fun init()
    fun updateHousesList()
}
