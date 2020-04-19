package com.tracker.covid.data

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.tracker.covid.BuildConfig
import com.tracker.covid.data.remote.NetworkService
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

object Networking {

    private const val NETWORK_CALL_TIMEOUT: Long = 60

    fun create(baseUrl: String, cacheDir: File, cacheSize: Long) : NetworkService {
         val retrofit =  Retrofit.Builder()
            .baseUrl(baseUrl)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient.Builder()
                    .cache(Cache(cacheDir, cacheSize))
                    .addInterceptor(
                        HttpLoggingInterceptor().apply {
                            level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
                            else HttpLoggingInterceptor.Level.NONE
                        }
                    )
                    .readTimeout(NETWORK_CALL_TIMEOUT , TimeUnit.SECONDS)
                    .writeTimeout(NETWORK_CALL_TIMEOUT , TimeUnit.SECONDS)
                    .build()
            ).build()
        return retrofit.create(NetworkService::class.java)

    }
}
