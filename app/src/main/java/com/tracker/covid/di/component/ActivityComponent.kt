package com.tracker.covid.di.component

import com.tracker.covid.di.ActivityScope
import com.tracker.covid.di.modules.ActivityModule
import com.tracker.covid.ui.home.HomeActivity
import dagger.Component

@ActivityScope
@Component(dependencies = [AppComponent::class], modules = [ActivityModule::class])
interface ActivityComponent {

    fun inject(homeActivity: HomeActivity)
}
