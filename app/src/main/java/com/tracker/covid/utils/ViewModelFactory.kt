package com.tracker.covid.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tracker.covid.data.remote.repositories.CoVidRepository
import com.tracker.covid.ui.home.HomeViewModel
import io.reactivex.disposables.CompositeDisposable

/**
 * A factory class to create custom view model with parameters.
 */
class ViewModelFactory(
    private val repository: CoVidRepository,
    private val compositeDisposable: CompositeDisposable,
    private val networkHelper: NetworkHelper
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            HomeViewModel(this.repository ,compositeDisposable , networkHelper) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }

}
