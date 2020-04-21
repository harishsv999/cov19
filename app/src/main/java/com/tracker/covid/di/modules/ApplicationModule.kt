package com.tracker.covid.di.modules

import android.app.Application
import com.tracker.covid.BuildConfig
import com.tracker.covid.data.Networking
import com.tracker.covid.data.remote.NetworkService
import com.tracker.covid.utils.NetworkHelper
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: Application) {

    @Provides
    fun getAppContext() = application

    @Provides
    fun provideNetworkService(networkHelper: NetworkHelper): NetworkService = Networking.create(
        BuildConfig.BASE_URL,
        networkHelper,
        application.cacheDir,
        10 * 1024 * 1024 // 10MB
    )

    @Provides
    fun provideCompositeDisposable(): CompositeDisposable = CompositeDisposable()

    @Singleton
    @Provides
    fun provideNetworkHelper(): NetworkHelper = NetworkHelper(application)
}
