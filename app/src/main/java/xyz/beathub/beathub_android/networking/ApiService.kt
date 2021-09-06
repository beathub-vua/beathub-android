package xyz.beathub.beathub_android.networking

import retrofit2.Call
import retrofit2.http.*
import xyz.beathub.beathub_android.CONTEXT_URL
import xyz.beathub.beathub_android.models.networking.AccountResponse
import xyz.beathub.beathub_android.models.networking.LoginRequest
import xyz.beathub.beathub_android.models.networking.RegisterRequest

interface ApiService {
    @POST("/accounts/register")
    fun register(@Body request: RegisterRequest): Call<LoginRequest>

    @POST("/login")
    fun login(@Body request: LoginRequest): Call<LoginRequest>

    @DELETE("$CONTEXT_URL/accounts/{id}")
    fun delete(@Path("id") id: String): Call<Void>

}