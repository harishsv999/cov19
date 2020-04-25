package com.tracker.covid.ui.countries

import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tracker.covid.R
import com.tracker.covid.di.component.FragmentComponent
import com.tracker.covid.ui.MainActivity
import com.tracker.covid.ui.base.BaseFragment
import com.tracker.covid.ui.home.HomeViewModel
import kotlinx.android.synthetic.main.fragment_countries.*

class CountriesFragment : BaseFragment<HomeViewModel>() {

    override fun provideLayout(): Int = R.layout.fragment_countries

    override fun setUpvView(view: View) {
        rcv_country.layoutManager = LinearLayoutManager(activity) as RecyclerView.LayoutManager?
        viewModel.countriesList.observe(activity as MainActivity, Observer {
                list->
            rcv_country.adapter = CountriesAdapter(list)
        })
    }
    override fun injectDependencies(fragmentComponent: FragmentComponent) {
     fragmentComponent.inject(this)
    }

}
