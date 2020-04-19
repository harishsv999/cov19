package com.tracker.covid.ui.home

import android.os.Bundle
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.tracker.covid.CoVidApplication
import com.tracker.covid.R
import com.tracker.covid.data.remote.repositories.CoVidRepository
import com.tracker.covid.di.component.DaggerActivityComponent
import com.tracker.covid.di.modules.ActivityModule
import com.tracker.covid.utils.NetworkHelper
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class HomeActivity : AppCompatActivity() {

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
        setContentView(R.layout.activity_main)

        DaggerActivityComponent.builder()
            .appComponent((application as CoVidApplication).appComponent)
            .activityModule(ActivityModule(this))
            .build()
            .inject(this)

        viewModel.fetchGlobalCoVidData()
        viewModel.getCountriesList()
        viewModel.getCountryRecordsByDay("pass country slug here, currently hardcoded")

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
            Toast.makeText(this@HomeActivity, it.toString(), Toast.LENGTH_LONG).show()
        })
    }


    private fun showMessage(message: String) =
        this.let { Toast.makeText(this, message, Toast.LENGTH_LONG).show() }

    private fun showMessage(@StringRes resId: Int) = showMessage(getString(resId))


}
