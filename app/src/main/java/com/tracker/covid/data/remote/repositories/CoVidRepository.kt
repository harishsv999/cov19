package com.tracker.covid.data.remote.repositories

import com.tracker.covid.BuildConfig
import com.tracker.covid.data.remote.EndPoints
import com.tracker.covid.data.remote.NetworkService
import com.tracker.covid.data.remote.model.CountryCodes
import com.tracker.covid.data.remote.response.CountryCases
import com.tracker.covid.data.remote.response.GlobalSummary
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable

class CoVidRepository(
    var compositeDisposable: CompositeDisposable,
    val networkService: NetworkService
) {


    fun getGlobalCoVidSummary(): Single<GlobalSummary> {
        return networkService.getCoVidSummary()
    }

    fun getCountriesList(): Single<List<CountryCodes>> {
        return networkService.getAllCountries()
    }

    fun getCountryRecordsByDay(countrySlug: String): Single<List<CountryCases>> {
        return networkService.getAllRecordsSinceFirstDay(BuildConfig.BASE_URL + EndPoints.SINCE_DAY_ONE + "south-africa")
    }

}
