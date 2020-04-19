package com.tracker.covid.data.remote.repositories

import androidx.lifecycle.MutableLiveData
import com.tracker.covid.data.remote.NetworkService
import com.tracker.covid.data.remote.response.GlobalSummary
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class CoVidRepository(
    var compositeDisposable: CompositeDisposable,
    val networkService: NetworkService
) {


    fun getGlobalCoVidSummary(): Single<GlobalSummary> {

      return  networkService.getCoVidSummary()

    }

}
