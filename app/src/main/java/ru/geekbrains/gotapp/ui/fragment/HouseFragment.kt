package ru.geekbrains.geekbrains_popular_libraries_kotlin.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.terrakok.cicerone.Router
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.geekbrains.geekbrains_popular_libraries_kotlin.R
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.api.IDataSource
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity.House
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity.room.db.Database
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.network.INetworkStatus
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.presenter.HousePresenter
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.view.HouseView
import ru.geekbrains.geekbrains_popular_libraries_kotlin.ui.App
import ru.geekbrains.geekbrains_popular_libraries_kotlin.ui.BackButtonListener
import ru.geekbrains.geekbrains_popular_libraries_kotlin.ui.adapter.HouseReposRVAdapter
import javax.inject.Inject

class HouseFragment : MvpAppCompatFragment(), HouseView, BackButtonListener {
    @Inject lateinit var api: IDataSource
    @Inject lateinit var networkStatus: INetworkStatus
    @Inject lateinit var database: Database
    @Inject lateinit var router: Router

    companion object {
        private const val HOUSE_ARG = "house"

        fun newInstance(house: House) = HouseFragment().apply {
            arguments = Bundle().apply {
                putParcelable(HOUSE_ARG, house)
            }
        }
    }

    val presenter by moxyPresenter {
        HousePresenter(
            this.arguments?.getParcelable<House>("house") as House
        ).apply { App.instance.appComponent.inject(this) }
    }
    val adapter by lazy {
        HouseReposRVAdapter(presenter.houseReposListPresenter)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = View.inflate(context, R.layout.fragment_house, null)
        return view
    }

    override fun onResume() {
        super.onResume()
        presenter.onResume()
    }

    override fun setNameToToolbar(userName: String?) {
        activity?.findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)?.title = userName
    }

    override fun removeNameFromToolbar() {
        activity?.findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)?.title = "GoTApp";
    }

    override fun init() {
        rv_repos.layoutManager = LinearLayoutManager(requireContext())
        rv_repos.adapter = adapter
    }

    override fun updateUsersList() {
        adapter.notifyDataSetChanged()
    }

    override fun backPressed() = presenter.backPressed()

}