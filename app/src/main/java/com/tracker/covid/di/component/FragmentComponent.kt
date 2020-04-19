package com.tracker.covid.di.component

import com.tracker.covid.data.remote.repositories.CoVidRepository
import com.tracker.covid.di.ActivityScope
import com.tracker.covid.di.FragmentScope
import com.tracker.covid.di.modules.FragmentModule
import com.tracker.covid.ui.countries.CountriesFragment
import dagger.Component

@ActivityScope
@Component(dependencies = [ AppComponent::class ],modules = [FragmentModule::class])
interface FragmentComponent {

    fun inject (countriesFragment: CountriesFragment)

}
