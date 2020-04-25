package com.tracker.covid.utils

import com.tracker.covid.BuildConfig

object StringUtils {

    fun createFlagUrl( countryCode : String) : String{
        return BuildConfig.BASE_URL_FLAG +"$countryCode/flat/64.png"
    }
}
