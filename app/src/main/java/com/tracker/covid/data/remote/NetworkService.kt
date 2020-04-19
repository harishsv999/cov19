package com.tracker.covid.data.remote

import com.tracker.covid.data.remote.response.GlobalSummary
import io.reactivex.Single
import retrofit2.http.GET

interface NetworkService {

    @GET(EndPoints.SUMMARY)
    fun getCoVidSummary(
    ): Single<GlobalSummary>


}
