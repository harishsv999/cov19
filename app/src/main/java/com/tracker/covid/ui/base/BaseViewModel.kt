package com.tracker.covid.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tracker.covid.R
import com.tracker.covid.utils.NetworkHelper
import com.tracker.covid.utils.Resource
import io.reactivex.disposables.CompositeDisposable
import javax.net.ssl.HttpsURLConnection

abstract class BaseViewModel (val compositeDisposable : CompositeDisposable ,
                     val networkHelper: NetworkHelper
) : ViewModel(){


    val messagedId: MutableLiveData<Resource<Int>> = MutableLiveData()
    val message:    MutableLiveData<Resource<String>> = MutableLiveData()

    protected fun checkInternetConnectionWithMessage(): Boolean =
            if (networkHelper.isNetworkConnected()) {
                true
            } else {
                messagedId.postValue(
                    Resource.error(
                        R.string.network_connection_error
                    )
                )
                false
            }

    protected fun checkInternetConnection(): Boolean = networkHelper.isNetworkConnected()

    protected fun handleNetworkError(err: Throwable?) =
            err?.let {
                networkHelper.castToNetworkError(it).run {
                    when (status) {
                        -1 -> messagedId.postValue(
                            Resource.error(
                                R.string.network_default_error
                            )
                        )
                        0 -> messagedId.postValue(
                            Resource.error(
                                R.string.server_connection_error
                            )
                        )
                        HttpsURLConnection.HTTP_UNAUTHORIZED -> {
                            messagedId.postValue(
                                Resource.error(
                                    R.string.server_connection_error
                                )
                            )
                        }
                        HttpsURLConnection.HTTP_INTERNAL_ERROR ->
                            messagedId.postValue(
                                Resource.error(
                                    R.string.network_internal_error
                                )
                            )
                        HttpsURLConnection.HTTP_UNAVAILABLE ->
                            messagedId.postValue(
                                Resource.error(
                                    R.string.network_server_not_available
                                )
                            )
                        else -> this@BaseViewModel.message.postValue(
                            Resource.error(
                                message
                            )
                        )
                    }
                }
            }


    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    abstract fun onCreate()

}
