package xyz.beathub.beathub_android.models.networking

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RegisterRequest (
    @SerialName("username") val username: String,
    @SerialName("password") val password: String,
    @SerialName("email") val email: String,
)