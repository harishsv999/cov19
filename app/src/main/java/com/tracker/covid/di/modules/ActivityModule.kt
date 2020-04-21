package com.tracker.covid.di.modules

import androidx.lifecycle.ViewModelProviders
import com.tracker.covid.data.remote.NetworkService
import com.tracker.covid.data.remote.repositories.CoVidRepository
import com.tracker.covid.ui.home.HomeActivity
import com.tracker.covid.ui.home.HomeViewModel
import com.tracker.covid.utils.NetworkHelper
import com.tracker.covid.ui.base.ViewModelFactory
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class ActivityModule(private val activity: HomeActivity) {

    @Provides
    fun provideCoVidRepository(
        compositeDisposable: CompositeDisposable,
        networkService: NetworkService
    ): CoVidRepository =
        CoVidRepository(compositeDisposable, networkService)

    @Provides
    fun provideViewModel(
        coVidRepository: CoVidRepository, compositeDisposable: CompositeDisposable,
        networkHelper: NetworkHelper
    ): HomeViewModel =
        ViewModelProviders.of(
            activity,
            ViewModelFactory(HomeViewModel::class) {
                HomeViewModel(coVidRepository, compositeDisposable, networkHelper)
            }).get(HomeViewModel::class.java)

    @Provides
    fun provideCompositeDisposable(): CompositeDisposable = CompositeDisposable()

}
