package xyz.beathub.beathub_android.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Playlist(
    @SerialName("code")val code: Int,
    @SerialName("region")val region: Region?,
    @SerialName("track")val track: Int?
)