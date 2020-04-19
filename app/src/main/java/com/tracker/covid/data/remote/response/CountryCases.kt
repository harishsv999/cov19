package com.tracker.covid.data.remote.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CountryCases(
    @Expose
    @SerializedName("Country")
    var countryName: String,

    @Expose
    @SerializedName("CountryCode")
    var countryCode: String,

    @Expose
    @SerializedName("Lat")
    var countryLat: String,

    @Expose
    @SerializedName("Lon")
    var countryLong: String,

    @Expose
    @SerializedName("Cases")
    var Cases: Int,

    @Expose
    @SerializedName("Status")
    var caseStatus: String,

    @Expose
    @SerializedName("Date")
    var date: String

) {
}
