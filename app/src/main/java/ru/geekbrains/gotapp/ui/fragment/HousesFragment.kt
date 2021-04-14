package ru.geekbrains.geekbrains_popular_libraries_kotlin.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View

import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.geekbrains.geekbrains_popular_libraries_kotlin.R
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.presenter.HousesPresenter
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.view.HousesView
import ru.geekbrains.geekbrains_popular_libraries_kotlin.ui.App
import ru.geekbrains.geekbrains_popular_libraries_kotlin.ui.BackButtonListener
import ru.geekbrains.geekbrains_popular_libraries_kotlin.ui.adapter.HousesRVAdapter

class HousesFragment : MvpAppCompatFragment(), HousesView, BackButtonListener {


    companion object {
        fun newInstance() = HousesFragment()
    }

    val presenter: HousesPresenter by moxyPresenter { App.instance.appComponent.inject(this)

        HousesPresenter().apply {
            App.instance.appComponent.inject(this)
        }
    }
    val adapter by lazy {
        HousesRVAdapter(presenter.housesListPresenter).apply { App.instance.appComponent.inject(this) }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        View.inflate(context, R.layout.fragment_houses, null)

    override fun init() {
        rv_houses.layoutManager = LinearLayoutManager(requireContext())
        rv_houses.adapter = adapter
    }
    override fun updateHousesList() {
        adapter.notifyDataSetChanged()
    }
    override fun backPressed() = presenter.backPressed()
}