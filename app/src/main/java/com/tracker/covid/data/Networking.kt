package com.tracker.covid.data

import android.content.ContentValues
import android.util.Log
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.tracker.covid.BuildConfig
import com.tracker.covid.data.remote.NetworkService
import com.tracker.covid.utils.NetworkHelper
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.io.IOException
import java.util.concurrent.TimeUnit

object Networking {

    private const val NETWORK_CALL_TIMEOUT: Long = 60
    private val HEADER_CACHE_CONTROL = "Cache-Control"
    private val HEADER_PRAGMA = "Pragma"

    fun create(baseUrl: String, networkHelper: NetworkHelper,cacheDir: File, cacheSize: Long): NetworkService {
        val retrofit = Retrofit.Builder()
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
                    .addInterceptor(offlineInterceptor(networkHelper))
                    .addInterceptor(networkInterceptor())
                    .readTimeout(NETWORK_CALL_TIMEOUT, TimeUnit.SECONDS)
                    .writeTimeout(NETWORK_CALL_TIMEOUT, TimeUnit.SECONDS)
                    .build()
            ).build()
        return retrofit.create(NetworkService::class.java)
    }


    /**
     * This interceptor will be called both if the network is available and if the network is not available
     * @return
     */
    private fun offlineInterceptor(networkHelper: NetworkHelper): Interceptor {
        return object : Interceptor {
            @Throws(IOException::class)
            override fun intercept(chain: Interceptor.Chain): Response {
                Log.d(ContentValues.TAG, "offline interceptor: called.")
                var request = chain.request()

                // prevent caching when network is on. For that we use the "networkInterceptor"
                if (!networkHelper.isNetworkConnected()) {
                    val cacheControl = CacheControl.Builder()
                        .maxStale(1, TimeUnit.DAYS)
                        .build()
                    request = request.newBuilder()
                        .removeHeader(HEADER_PRAGMA)
                        .removeHeader(HEADER_CACHE_CONTROL)
                        .cacheControl(cacheControl)
                        .build()
                }
                return chain.proceed(request)
            }
        }
    }


    /**
     * This interceptor will be called ONLY if the network is available
     * @return
     */
    private fun networkInterceptor(): Interceptor {
        return object : Interceptor {
            @Throws(IOException::class)
            override fun intercept(chain: Interceptor.Chain): Response {
                Log.d(ContentValues.TAG, "network interceptor: called.")
                val response = chain.proceed(chain.request())
                val cacheControl = CacheControl.Builder()
                    .maxAge(5, TimeUnit.MINUTES)
                    .build()
                return response.newBuilder()
                    .removeHeader(HEADER_PRAGMA)
                    .removeHeader(HEADER_CACHE_CONTROL)
                    .header(HEADER_CACHE_CONTROL, cacheControl.toString())
                    .build()
            }
        }
    }
}
