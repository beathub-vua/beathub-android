package xyz.beathub.beathub_android.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import xyz.beathub.beathub_android.models.AudioFile
import xyz.beathub.beathub_android.models.Playlist


@Serializable
data class Region(
    @SerialName("id")val id: Int,
    @SerialName("name")val name: String?,
    @SerialName("playlist")val playlist: Int?,
    @SerialName("audioFiles")val audioFiles: List<AudioFile>
)