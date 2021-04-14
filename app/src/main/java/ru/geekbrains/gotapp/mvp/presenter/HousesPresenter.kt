package ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.presenter

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity.House
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.repo.IHousesRepo
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.presenter.list.IHouseListPresenter
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.presenter.list.IUserListPresenter
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.view.HousesView
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.view.list.HouseItemView
import ru.geekbrains.gotapp.di.house.IHouseScopeContainer
import javax.inject.Inject

class HousesPresenter() : MvpPresenter<HousesView>() {

    @Inject lateinit var router: Router
    @Inject lateinit var housesRepoRetrofit: IHousesRepo
    @Inject lateinit var uiScheduler: Scheduler
    @Inject lateinit var scope: IHouseScopeContainer
    class HousesListPresenter : IHouseListPresenter {
        val houses = mutableListOf<House>()
        override var itemClickListener: ((HouseItemView) -> Unit)? = null

        override fun getCount() = houses.size

        override fun bindView(view: HouseItemView) {
            val house = houses[view.pos]
            view.setName(house.name)
        }
    }
    val compositeDisposable = CompositeDisposable()
    val housesListPresenter = HousesListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()

        housesListPresenter.itemClickListener = { itemView ->
            val house = housesListPresenter.houses[itemView.pos]
            router.navigateTo(screens.house(house))
        }
    }

    fun loadData() {
        val disposable = housesRepoRetrofit.getHouses()
            .observeOn(uiScheduler)
            .subscribe({
                housesListPresenter.houses.addAll(it)
                viewState.updateList()
            }, {
                it.printStackTrace()
            })

        compositeDisposable.add(disposable)
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

    override fun onDestroy() {
        compositeDisposable.dispose()
        super.onDestroy()
        scope.releaseHouseScope()
    }

}
