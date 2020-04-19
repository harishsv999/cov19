package com.tracker.covid.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tracker.covid.data.remote.model.CountryCodes
import com.tracker.covid.data.remote.repositories.CoVidRepository
import com.tracker.covid.data.remote.response.CountryCases
import com.tracker.covid.data.remote.response.GlobalSummary
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class HomeViewModel(
    private val coVidRepository: CoVidRepository,
    private val compositeDisposable: CompositeDisposable
) : ViewModel() {

    val globalCoVidData = MutableLiveData<GlobalSummary>()
    val countriesList = MutableLiveData<List<CountryCodes>>()
    val countryRecordsByDay = MutableLiveData<List<CountryCases>>()

    fun fetchGlobalCoVidData() {
        compositeDisposable.addAll(
            coVidRepository.getGlobalCoVidSummary()
                .subscribeOn(Schedulers.io())
                .subscribe({
                    globalCoVidData.postValue(it)
                }, {
                    val v = it.toString()
                    // handleNetworkError(it)
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
                    val v = it.toString()
                    // handleNetworkError(it)
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
                    val v = it.toString()
                    // handleNetworkError(it)
                })
        )
    }


}
