package com.tracker.covid

import android.app.Application
import com.tracker.covid.di.component.AppComponent
import com.tracker.covid.di.component.DaggerAppComponent
import com.tracker.covid.di.modules.ApplicationModule
import com.tracker.covid.utils.NetworkHelper
import javax.inject.Inject

class CoVidApplication : Application() {

    @Inject
    lateinit var networkHelper: NetworkHelper

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder()
            .applicationModule(ApplicationModule(this))
            .build()
        appComponent.inject(this)
    }
}
