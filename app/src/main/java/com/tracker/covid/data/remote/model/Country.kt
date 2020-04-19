package com.tracker.covid.data.remote.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Country(

    @Expose
    @SerializedName("Country")
    var countryName: String,

    @Expose
    @SerializedName("CountryCode")
    var countryCode: String,

    @Expose
    @SerializedName("Slug")
    var countrySlug: String,

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
    var totalRecoveredCases: Long,

    @Expose
    @SerializedName("Date")
    var date: String


)
