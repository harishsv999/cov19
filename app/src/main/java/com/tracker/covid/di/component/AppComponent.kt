package com.tracker.covid.di.component

import com.tracker.covid.CoVidApplication
import com.tracker.covid.data.remote.NetworkService
import com.tracker.covid.di.modules.ApplicationModule
import com.tracker.covid.utils.NetworkHelper
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface AppComponent {

    fun inject(application: CoVidApplication)

    fun getNetworkService(): NetworkService

    fun getNetworkHelper(): NetworkHelper
}
