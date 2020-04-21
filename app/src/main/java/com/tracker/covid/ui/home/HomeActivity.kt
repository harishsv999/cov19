package com.tracker.covid.ui.home

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.tracker.covid.CoVidApplication
import com.tracker.covid.R
import com.tracker.covid.data.remote.repositories.CoVidRepository
import com.tracker.covid.di.component.DaggerActivityComponent
import com.tracker.covid.di.modules.ActivityModule
import com.tracker.covid.ui.countries.CountriesFragment
import com.tracker.covid.utils.NetworkHelper
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupNavigation()
    }

    private fun setupNavigation() {
        val navController = findNavController(R.id.navGraph)
        bottomBar.setupWithNavController(navController)
    }

    override fun onSupportNavigateUp() =
        findNavController(R.id.navGraph).navigateUp()
}
