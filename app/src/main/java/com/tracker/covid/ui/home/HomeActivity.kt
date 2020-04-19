package com.tracker.covid.ui.home

import android.os.Bundle
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.tracker.covid.BuildConfig
import com.tracker.covid.R
import com.tracker.covid.data.Networking
import com.tracker.covid.data.remote.NetworkService
import com.tracker.covid.data.remote.repositories.CoVidRepository
import com.tracker.covid.utils.NetworkHelper
import com.tracker.covid.utils.ViewModelFactory
import io.reactivex.disposables.CompositeDisposable

class HomeActivity : AppCompatActivity() {

    private lateinit var coVidRepository: CoVidRepository

    private lateinit var viewModel: HomeViewModel

    private lateinit var networkHelper: NetworkHelper

    private lateinit var compositeDisposable: CompositeDisposable


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val networkService = Networking.create(
            BuildConfig.BASE_URL,
            application.cacheDir,
            10 * 1024 * 1024 // 10MB
        )
        compositeDisposable = CompositeDisposable()
        coVidRepository = CoVidRepository(compositeDisposable, networkService)
        networkHelper = NetworkHelper(this)
        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(coVidRepository, compositeDisposable, networkHelper)
        ).get(HomeViewModel::class.java)

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
