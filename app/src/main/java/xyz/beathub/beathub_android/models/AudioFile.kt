package xyz.beathub.beathub_android.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class AudioFile(
    @SerialName("code")val code: Int,
    @SerialName("filePath")val filePath: String?,
    @SerialName("region")val region: Int?,
    @SerialName("track")val track: Int?
)