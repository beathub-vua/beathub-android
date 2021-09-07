package xyz.beathub.beathub_android.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Repo (
    @SerialName("id")val repoId: Int = 0,
    @SerialName("name")var name: String,
    @SerialName("description")val description: String,
    @SerialName("dateCreated")val dateCreated: String,
    @SerialName("commits")val commits: String?,

)