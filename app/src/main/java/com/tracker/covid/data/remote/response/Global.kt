package com.tracker.covid.data.remote.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Global (
    @Expose
    @SerializedName("NewConfirmed")
    var newConfirmedCases: Long,

    @Expose
    @SerializedName("TotalConfirmed")
    var totalConfirmedCases: Long,

    @Expose
    @SerializedName("NewDeaths")
    var newDeathCases: Long,

    @Expose
    @SerializedName("TotalDeaths")
    var totalDeathCases: Long,

    @Expose
    @SerializedName("NewRecovered")
    var newRecoveredCases: Long,

    @Expose
    @SerializedName("TotalRecovered")
    var totalRecoveredCases: Long

    ){
}
