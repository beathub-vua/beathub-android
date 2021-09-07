package xyz.beathub.beathub_android.models

import kotlinx.android.parcel.IgnoredOnParcel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Plugin (
    @SerialName("id")val pluginId: Int = 0,
    @SerialName("uniqueId")var uniqueId: Int?,
    @SerialName("routeId")var routeId: Int,
    @SerialName("name")val name: String,
    @SerialName("parameters")val parameters: String?,
)