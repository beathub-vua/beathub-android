package xyz.beathub.beathub_android.models.networking

import kotlinx.serialization.Serializable

@Serializable
data class AccountResponseItem(
    val accountNonExpired: Boolean,
    val accountNonLocked: Boolean,
    val authorities: List<Authority>,
    val credentialsNonExpired: Boolean,
    val dateCreated: String,
    val email: String,
    val enabled: Boolean,
    val id: Int,
    val password: String,
    val role: String,
    val username: String
)