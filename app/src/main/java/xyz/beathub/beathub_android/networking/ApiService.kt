package xyz.beathub.beathub_android.networking

import retrofit2.Call
import retrofit2.http.*
import xyz.beathub.beathub_android.CONTEXT_URL
import xyz.beathub.beathub_android.models.Commit
import xyz.beathub.beathub_android.models.Repo
import xyz.beathub.beathub_android.models.networking.LoginRequest
import xyz.beathub.beathub_android.models.networking.RegisterRequest

interface ApiService {
    @POST("/accounts/register")
    fun register(@Body request: RegisterRequest): Call<LoginRequest>

    @POST("/login")
    fun login(@Body request: LoginRequest): Call<LoginRequest>

    @DELETE("$CONTEXT_URL/accounts/{id}")
    fun delete(@Path("id") id: String): Call<Void>

    @GET("$CONTEXT_URL/projects/{id}")
    fun getProjects(@Path("id") id: String): Call<List<Repo>>

    @GET("$CONTEXT_URL/commits")
    fun getCommits(@Query("projectId") id: Int): Call<List<Commit>>

}