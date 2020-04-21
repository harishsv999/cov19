package com.tracker.covid.di.modules

import android.content.Context
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.tracker.covid.data.remote.NetworkService
import com.tracker.covid.data.remote.repositories.CoVidRepository
import com.tracker.covid.ui.home.HomeViewModel
import com.tracker.covid.ui.base.BaseFragment
import com.tracker.covid.utils.NetworkHelper
import com.tracker.covid.ui.base.ViewModelFactory
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class FragmentModule(private val fragment: BaseFragment<*>) {

    @Provides
    fun provideCoVidRepository(
        compositeDisposable: CompositeDisposable,
        networkService: NetworkService
    ): CoVidRepository =
        CoVidRepository(compositeDisposable, networkService)

    @Provides
    fun provideLayoutManager(): LinearLayoutManager = LinearLayoutManager(fragment as Context)

    @Provides
    fun provideCompositeDisposable(): CompositeDisposable = CompositeDisposable()

    @Provides
    fun provideViewModel(
        coVidRepository: CoVidRepository,
        compositeDisposable: CompositeDisposable,
        networkHelper: NetworkHelper
    ): HomeViewModel = fragment?.let {
        ViewModelProviders.of(fragment,
            ViewModelFactory(HomeViewModel::class) {
                HomeViewModel(coVidRepository, compositeDisposable, networkHelper)
            }).get(HomeViewModel::class.java)

    }

}
