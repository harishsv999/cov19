package com.tracker.covid.di.component

import com.tracker.covid.data.remote.repositories.CoVidRepository
import com.tracker.covid.di.ActivityScope
import com.tracker.covid.di.modules.ActivityModule
import com.tracker.covid.ui.MainActivity
import com.tracker.covid.ui.countries.CountriesFragment
import dagger.Component

@ActivityScope
@Component(dependencies = [AppComponent::class], modules = [ActivityModule::class])
interface ActivityComponent {

    fun inject(mainActivity: MainActivity)

    fun inject(countriesFragment: CountriesFragment)

}
