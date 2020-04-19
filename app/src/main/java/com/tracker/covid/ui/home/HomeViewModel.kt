package com.tracker.covid.ui.home

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tracker.covid.data.remote.repositories.CoVidRepository
import com.tracker.covid.data.remote.response.GlobalSummary
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class HomeViewModel (val coVidRepository: CoVidRepository , val compositeDisposable: CompositeDisposable): ViewModel() {

    val globalCoVidData  = MutableLiveData<GlobalSummary>()


    fun fetchGlobalCoVidData() {
        compositeDisposable.addAll(
            coVidRepository.getGlobalCoVidSummary()
                .subscribeOn(Schedulers.io())
                .subscribe({
                    globalCoVidData.postValue(it)
                }, {
                    val v =  it.toString()
                   // handleNetworkError(it)
                })
        )
    }

    fun getCountriesList() {
        compositeDisposable.addAll(
            coVidRepository.getGlobalCoVidSummary()
                .subscribeOn(Schedulers.io())
                .subscribe({
                    globalCoVidData.postValue(it)
                }, {
                    val v =  it.toString()
                    // handleNetworkError(it)
                })
        )
    }

}
