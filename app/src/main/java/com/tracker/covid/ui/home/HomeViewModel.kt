package com.tracker.covid.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tracker.covid.R
import com.tracker.covid.data.remote.model.CountryCodes
import com.tracker.covid.data.remote.repositories.CoVidRepository
import com.tracker.covid.data.remote.response.CountryCases
import com.tracker.covid.data.remote.response.GlobalSummary
import com.tracker.covid.utils.NetworkHelper
import com.tracker.covid.utils.Resource
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.net.ssl.HttpsURLConnection

class HomeViewModel(
    private val coVidRepository: CoVidRepository,
    private val compositeDisposable: CompositeDisposable,
    private val networkHelper: NetworkHelper
) : ViewModel() {

    val globalCoVidData = MutableLiveData<GlobalSummary>()
    private val countriesList = MutableLiveData<List<CountryCodes>>()
    val countryRecordsByDay = MutableLiveData<List<CountryCases>>()

    val messageStringId: MutableLiveData<Resource<Int>> = MutableLiveData()
    val messageString: MutableLiveData<Resource<String>> = MutableLiveData()

    fun fetchGlobalCoVidData() {
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

    /**
     * Function to handle the error in network calling.
     */
    private fun handleNetworkError(err: Throwable?) =
        err?.let {
            networkHelper.castToNetworkError(it).run {
                when (status) {
                    -1 -> messageStringId.postValue(Resource.error(R.string.network_connection_error))
                    0 -> messageStringId.postValue(Resource.error(R.string.server_connection_error))
                    HttpsURLConnection.HTTP_UNAUTHORIZED -> {
                        messageStringId.postValue(Resource.error(R.string.server_connection_error))
                    }
                    HttpsURLConnection.HTTP_INTERNAL_ERROR ->
                        messageStringId.postValue(Resource.error(R.string.network_internal_error))
                    HttpsURLConnection.HTTP_UNAVAILABLE ->
                        messageStringId.postValue(Resource.error(R.string.network_server_not_available))
                    else -> messageString.postValue(Resource.error(message))
                }
            }
        }


}
