package xyz.beathub.beathub_android.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Commit (
    @SerialName("id")val commitId: Int = 0,
    @SerialName("projectName")var message: String,
    @SerialName("description")val filePath: String,
    @SerialName("dateCreated")val dateTime: String,
    @SerialName("tracks")val tracks: String?,
    @SerialName("current")val current: Boolean,
)