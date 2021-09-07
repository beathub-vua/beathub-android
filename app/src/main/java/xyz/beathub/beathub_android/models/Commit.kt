package xyz.beathub.beathub_android.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Commit (
    @SerialName("id")val commitId: Int = 0,
    @SerialName("projectName")var message: String,
    @SerialName("filePath")val filePath: String,
    @SerialName("dateTime")val dateTime: String,
    @SerialName("tracks")val tracks: List<Track>,
    @SerialName("current")val current: Boolean,
)