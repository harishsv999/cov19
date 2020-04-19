package com.tracker.covid.data.remote.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class GlobalSummary(

    @Expose
    @SerializedName("Global")
    var globalData: Global,

    @Expose
    @SerializedName("Countries")
    var countriesList: List<Country>

)
