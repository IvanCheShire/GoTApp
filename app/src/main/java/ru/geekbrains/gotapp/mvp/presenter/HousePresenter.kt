package ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.presenter

import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import moxy.MvpPresenter
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity.HouseRepository
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity.House
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.repo.IHouseReposRepo
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.navigation.Screens
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.presenter.list.IHouseReposListPresenter
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.view.HouseView
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.view.list.RepoItemView
import ru.geekbrains.gotapp.di.repository.IRepositoryScopeContainer
import javax.inject.Inject

class HousePresenter(val user: House) : MvpPresenter<HouseView>() {

    @Inject lateinit var router: Router
    @Inject lateinit var houseReposRepo: IHouseReposRepo
    @Inject lateinit var uiScheduler: Scheduler
    @Inject lateinit var scope: IRepositoryScopeContainer

    class HouseReposListPresenter : IHouseReposListPresenter {
        override var itemClickListener: ((RepoItemView) -> Unit)? = null

        val repos = mutableListOf<HouseRepository>()

        override fun bindView(view: RepoItemView) {
            val repo = repos[view.pos]
            view.setName(repo.name)
        }
        override fun getCount() = repos.size
    }

    val userReposListPresenter = HouseReposListPresenter()

    var disposables = CompositeDisposable()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()

        userReposListPresenter.itemClickListener = { view ->
            val repository = userReposListPresenter.repos[view.pos]
            router.navigateTo(Screens.RepositoryScreen(repository))
        }
    }


    fun loadData() {
        houseReposRepo.getHouseRepos(house)
            .retry(3)
            .observeOn(uiScheduler)
            .subscribe(
                        {
                            userReposListPresenter.repos.clear()
                            userReposListPresenter.repos.addAll(it)
                            viewState.updateHousesList()
                        },
                        { println("onError: ${it.message}") })


    }
    fun onResume(){
        viewState.setNameToToolbar(houseName = house.name)
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
    override fun onDestroy() {
        super.onDestroy()
        viewState.removeNameFromToolbar()
        disposables.dispose()
        scope.releaseRepositoryScope()
    }
}
