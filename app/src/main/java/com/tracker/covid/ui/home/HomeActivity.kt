package com.tracker.covid.ui.home

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.tracker.covid.BuildConfig
import com.tracker.covid.R
import com.tracker.covid.data.Networking
import com.tracker.covid.data.remote.repositories.CoVidRepository
import com.tracker.covid.utils.ViewModelFactory
import io.reactivex.disposables.CompositeDisposable

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val networkService = Networking.create(
            BuildConfig.BASE_URL,
            application.cacheDir,
            10 * 1024 * 1024 // 10MB
        )
        val coVidRepository = CoVidRepository(CompositeDisposable(), networkService)
        val viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(coVidRepository, CompositeDisposable())
        ).get(HomeViewModel::class.java)

        viewModel.fetchGlobalCoVidData()
        viewModel.getCountriesList()
        viewModel.getCountryRecordsByDay("pass country slug here, currently hardcoded")

        viewModel.globalCoVidData.observe(this, Observer {
            Toast.makeText(this@HomeActivity, it.toString(), Toast.LENGTH_LONG).show()

        })
    }
}
