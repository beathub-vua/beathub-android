package xyz.beathub.beathub_android.models

import kotlinx.android.parcel.IgnoredOnParcel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Plugin (
    @SerialName("code")val pluginId: Int?,
    @SerialName("uniqueId")var uniqueId: String?,
    @SerialName("routeId")var routeId: Int?,
    @SerialName("name")val name: String?,
    @SerialName("dllPath")val dllPath: String?,
    @SerialName("parameters")val parameters: List<Parameter>,
    @SerialName("track")val track: Int?,

)