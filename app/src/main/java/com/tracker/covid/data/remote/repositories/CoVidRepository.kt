package com.tracker.covid.data.remote.repositories

import com.tracker.covid.BuildConfig
import com.tracker.covid.data.remote.EndPoints
import com.tracker.covid.data.remote.NetworkService
import com.tracker.covid.data.remote.model.CountryCodes
import com.tracker.covid.data.remote.response.CountryCases
import com.tracker.covid.data.remote.response.GlobalSummary
import com.tracker.covid.utils.StringUtils
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


class CoVidRepository(
    var compositeDisposable: CompositeDisposable,
    val networkService: NetworkService
) {

   private var mylist: MutableList<CountryCodes> = mutableListOf()

    fun getGlobalCoVidSummary(): Single<GlobalSummary> {
        return networkService.getCoVidSummary()
    }

    fun getCountriesList() : Single<List<CountryCodes>> {
        return networkService.getAllCountries()
            .subscribeOn(Schedulers.io())
            .flatMap {
                mylist.clear()
                it.forEach {
                    mylist.add(
                        CountryCodes(
                            countryName = it.countryName,
                            countrySlug = it.countrySlug,
                            countryCode = it.countryCode,
                            countryFlagURL = StringUtils.createFlagUrl(it.countryCode)
                        )
                    )
                }
                mylist.sortBy { it.countryName }
                Single.fromObservable(Observable.fromArray(mylist))
            }
    }


    fun getCountryRecordsByDay(countrySlug: String): Single<List<CountryCases>> {
        return networkService.getAllRecordsSinceFirstDay(BuildConfig.BASE_URL + EndPoints.SINCE_DAY_ONE + "south-africa")
    }

}
