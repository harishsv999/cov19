package com.tracker.covid.di.component

import com.tracker.covid.data.remote.repositories.CoVidRepository
import com.tracker.covid.di.ActivityScope
import com.tracker.covid.di.modules.ActivityModule
import com.tracker.covid.ui.countries.CountriesFragment
import com.tracker.covid.ui.home.HomeActivity
import com.tracker.covid.ui.home.HomeFragment
import dagger.Component

@ActivityScope
@Component(dependencies = [AppComponent::class], modules = [ActivityModule::class])
interface ActivityComponent {

    fun inject(homeActivity: HomeActivity)

    fun inject(countriesFragment: CountriesFragment)

    fun inject(HomeFragment: HomeFragment)

}