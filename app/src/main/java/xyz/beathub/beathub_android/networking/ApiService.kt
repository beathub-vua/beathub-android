package xyz.beathub.beathub_android.networking

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import xyz.beathub.beathub_android.models.networking.LoginRequest
import xyz.beathub.beathub_android.models.networking.RegisterRequest

interface ApiService {
    @POST("/accounts/register")
    fun register(@Body request: RegisterRequest): Call<LoginRequest>

    @POST("/login")
    fun login(@Body request: LoginRequest): Call<LoginRequest>
}