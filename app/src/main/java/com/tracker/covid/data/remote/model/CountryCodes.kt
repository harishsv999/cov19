package com.tracker.covid.data.remote.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CountryCodes(

    @Expose
    @SerializedName("Country")
    var countryName: String,

    @Expose
    @SerializedName("Slug")
    var countrySlug: String,

    @Expose
    @SerializedName("ISO2")
    var countryCode: String,

    var countryFlagURL: String
)
