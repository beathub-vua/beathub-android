package xyz.beathub.beathub_android.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class User(
    @SerialName("id")val userId: String,
    @SerialName("email")var email: String,
    @SerialName("image_url")var imageUrl: String?
)