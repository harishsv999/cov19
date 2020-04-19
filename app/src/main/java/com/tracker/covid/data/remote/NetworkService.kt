package com.tracker.covid.data.remote

import com.tracker.covid.data.remote.model.CountryCodes
import com.tracker.covid.data.remote.response.CountryCases
import com.tracker.covid.data.remote.response.GlobalSummary
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Url

interface NetworkService {

    @GET(EndPoints.SUMMARY)
    fun getCoVidSummary(
    ): Single<GlobalSummary>

    @GET(EndPoints.COUNTRIES)
    fun getAllCountries(
    ): Single<List<CountryCodes>>

    @GET
    fun getAllRecordsSinceFirstDay(@Url url: String): Single<List<CountryCases>>


}
