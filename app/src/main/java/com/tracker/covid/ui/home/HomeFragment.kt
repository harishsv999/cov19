package com.tracker.covid.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import com.tracker.covid.CoVidApplication
import com.tracker.covid.R
import com.tracker.covid.data.remote.repositories.CoVidRepository
import com.tracker.covid.di.component.DaggerActivityComponent
import com.tracker.covid.di.component.DaggerFragmentComponent
import com.tracker.covid.di.modules.ActivityModule
import com.tracker.covid.di.modules.FragmentModule
import com.tracker.covid.ui.countries.CountriesFragment
import com.tracker.covid.utils.NetworkHelper
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class HomeFragment : Fragment(){

    @Inject
    lateinit var coVidRepository: CoVidRepository

    @Inject
    lateinit var viewModel: HomeViewModel

    @Inject
    lateinit var networkHelper: NetworkHelper

    @Inject
    lateinit var compositeDisposable: CompositeDisposable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.fetchGlobalCoVidData()
        viewModel.getCountriesList()
        viewModel.getCountryRecordsByDay("pass country slug here, currently hardcoded")

        DaggerFragmentComponent.builder()
            .appComponent((activity?.application as CoVidApplication).appComponent)
            .fragmentModule(FragmentModule(this))
            .build()
            .inject(this)

        subscribeDataListener()
        subscribeErrorListener()
    }

    private fun subscribeErrorListener() {
        viewModel.messageString.observe(this, Observer {
            it.data?.run { showMessage(this) }
        })

        viewModel.messageStringId.observe(this, Observer {
            it.data?.run { showMessage(this) }
        })

    }

    private fun subscribeDataListener() {
        viewModel.globalCoVidData.observe(this, Observer {
            showMessage(it.toString())
        })
    }


    private fun showMessage(message: String) =
        this.let { Toast.makeText(activity, message, Toast.LENGTH_LONG).show() }

    private fun showMessage(@StringRes resId: Int) = showMessage(getString(resId))



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_home, container, false)
        return rootView
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }
}