package com.tracker.covid.ui.home

import android.os.Bundle
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import com.tracker.covid.CoVidApplication
import com.tracker.covid.R
import com.tracker.covid.data.remote.repositories.CoVidRepository
import com.tracker.covid.di.component.DaggerActivityComponent
import com.tracker.covid.di.modules.ActivityModule
import com.tracker.covid.ui.countries.CountriesFragment
import com.tracker.covid.utils.NetworkHelper
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject


class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
        ft.add(R.id.container, CountriesFragment(), "NewFragmentTag")
        ft.commit()

    }

}
