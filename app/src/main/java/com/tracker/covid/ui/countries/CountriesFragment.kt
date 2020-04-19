package com.tracker.covid.ui.countries

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.tracker.covid.CoVidApplication
import com.tracker.covid.R
import com.tracker.covid.di.component.DaggerFragmentComponent
import com.tracker.covid.di.modules.FragmentModule
import com.tracker.covid.ui.home.HomeActivity
import com.tracker.covid.ui.home.HomeViewModel
import kotlinx.android.synthetic.main.fragment_countries.*
import javax.inject.Inject


class CountriesFragment : Fragment() {

    @Inject
    lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_countries, container, false)
        return rootView

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setUpObserver()
    }

    private fun setUpObserver(){
        DaggerFragmentComponent.builder()
            .appComponent((activity?.application as CoVidApplication).appComponent)
            .fragmentModule(FragmentModule(this))
            .build()
            .inject(this)

        rcv_country.layoutManager = LinearLayoutManager(activity)
        homeViewModel.getCountriesList()
        homeViewModel.countriesList.observe(activity as HomeActivity, Observer {
                list->
            rcv_country.adapter = CountriesAdapter(list)

        })

    }

}
