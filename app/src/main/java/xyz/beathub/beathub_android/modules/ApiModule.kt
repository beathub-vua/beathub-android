package xyz.beathub.beathub_android.modules

import android.app.Activity
import android.content.SharedPreferences
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import xyz.beathub.beathub_android.networking.ApiService


object ApiModule {
    private const val BASE_URL = "https://privateip1337.lets.ee:8443/api-0.0.1-SNAPSHOT"

    lateinit var retrofit: ApiService

    fun initRetrofit(sharedPref: SharedPreferences, activity: Activity) {
        val okhttp = OkHttpClient.Builder()
            .addInterceptor(
                ChuckerInterceptor.Builder(activity)
                    .collector(ChuckerCollector(activity))
                    .maxContentLength(250000L)
                    .redactHeaders(emptySet())
                    .alwaysReadResponseBody(false)
                    .build()
            )
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }).addInterceptor(Interceptor { chain ->

                val request =
                    chain.request().newBuilder().apply {
                            addHeader("token-type", "Bearer")
                            addHeader(
                                "access-token",
                                sharedPref.getString("USER_AUTH_ACCESS_TOKEN_TYPE_KEY", "")
                                    .orEmpty()
                            )
                            addHeader(
                                "client",
                                sharedPref.getString("USER_AUTH_CLIENT_TYPE_KEY", "").orEmpty()
                            )
                            addHeader(
                                "uid",
                                sharedPref.getString("USER_AUTH_UID_TYPE_KEY", "").orEmpty()
                            )
                    }.build()
                chain.proceed(request)
            })
            .build()

        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .client(okhttp)
            .build()
            .create(ApiService::class.java)
    }
}