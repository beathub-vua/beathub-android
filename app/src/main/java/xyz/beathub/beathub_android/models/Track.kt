package xyz.beathub.beathub_android.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Track (
    @SerialName("id")val trackId: Int = 0,
    @SerialName("trackName")var trackName: String,
    @SerialName("trackRouteId")var trackRouteId: Int?,
    @SerialName("channels")var channels: Int?,
    @SerialName("playlist")val playlist: Playlist?,
    @SerialName("audioFiles")val audioFiles: List<AudioFile>,
    @SerialName("plugins")val plugins: List<Plugin>,
    @SerialName("commit")val commit: Int?,
)