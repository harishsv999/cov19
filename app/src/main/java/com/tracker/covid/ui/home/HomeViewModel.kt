package com.tracker.covid.ui.home

import androidx.lifecycle.MutableLiveData
import com.tracker.covid.data.remote.model.CountryCodes
import com.tracker.covid.data.remote.repositories.CoVidRepository
import com.tracker.covid.data.remote.response.CountryCases
import com.tracker.covid.data.remote.response.GlobalSummary
import com.tracker.covid.ui.base.BaseViewModel
import com.tracker.covid.utils.NetworkHelper
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class HomeViewModel(
    private val coVidRepository: CoVidRepository,
    compositeDisposable: CompositeDisposable,
    networkHelper: NetworkHelper
) : BaseViewModel(compositeDisposable, networkHelper) {

    val globalCoVidData = MutableLiveData<GlobalSummary>()
    val countriesList = MutableLiveData<List<CountryCodes>>()
    val countryRecordsByDay = MutableLiveData<List<CountryCases>>()

    override fun onCreate() {
        fetchGlobalCoVidData()
        getCountriesList()
        getCountryRecordsByDay("")
    }

    private fun fetchGlobalCoVidData() {
        compositeDisposable.addAll(
            coVidRepository.getGlobalCoVidSummary()
                .subscribeOn(Schedulers.io())
                .subscribe({
                    globalCoVidData.postValue(it)
                }, {
                    handleNetworkError(it)
                })
        )
    }

    fun getCountriesList() {
        compositeDisposable.addAll(
            coVidRepository.getCountriesList()
                .subscribeOn(Schedulers.io())
                .subscribe({
                    countriesList.postValue(it)
                }, {
                    handleNetworkError(it)
                })
        )
    }

    fun getCountryRecordsByDay(countrySlug: String) {
        compositeDisposable.addAll(
            coVidRepository.getCountryRecordsByDay(countrySlug)
                .subscribeOn(Schedulers.io())
                .subscribe({
                    countryRecordsByDay.postValue(it)
                }, {
                    handleNetworkError(it)
                })
        )
    }


}
