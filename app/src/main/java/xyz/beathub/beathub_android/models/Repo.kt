package xyz.beathub.beathub_android.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Repo (
    @SerialName("id")val repoId: String = "0",
    @SerialName("name")var name: String
)