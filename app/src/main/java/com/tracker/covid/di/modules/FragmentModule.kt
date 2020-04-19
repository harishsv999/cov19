package com.tracker.covid.di.modules

import android.content.Context
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.tracker.covid.data.remote.NetworkService
import com.tracker.covid.data.remote.repositories.CoVidRepository
import com.tracker.covid.ui.countries.CountriesFragment
import com.tracker.covid.ui.home.HomeViewModel
import com.tracker.covid.utils.NetworkHelper
import com.tracker.covid.utils.ViewModelFactory
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

@Module
class FragmentModule (private val fragmentActivity: CountriesFragment) {

    @Provides
    fun provideCoVidRepository(
        compositeDisposable: CompositeDisposable,
        networkService: NetworkService
    ): CoVidRepository =
        CoVidRepository(compositeDisposable, networkService)

    @Provides
    fun provideLayoutManager() : LinearLayoutManager = LinearLayoutManager(fragmentActivity  as Context)

    @Provides
    fun provideCompositeDisposable() : CompositeDisposable = CompositeDisposable()

    @Provides
    fun provideViewModel(coVidRepository: CoVidRepository,
                         compositeDisposable: CompositeDisposable,
                         networkHelper: NetworkHelper) : HomeViewModel = fragmentActivity?.let {
        ViewModelProviders.of(
            fragmentActivity,
            ViewModelFactory(coVidRepository, compositeDisposable, networkHelper)
        ).get(HomeViewModel::class.java)
    }

}
