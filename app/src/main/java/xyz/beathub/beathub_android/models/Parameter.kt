package xyz.beathub.beathub_android.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Parameter(
    @SerialName("calcValue")val calcValue: Double?,
    @SerialName("id")val id: Int,
    @SerialName("max")val max: Double?,
    @SerialName("min")val min: Double?,
    @SerialName("name")val name: String?,
    @SerialName("parameterNumber")val parameterNumber: Int?,
    @SerialName("plugin")val plugin: Int?,
    @SerialName("toggle")val toggle: Boolean?,
    @SerialName("unit")val unit: Int?,
    @SerialName("value")val value: Double?
)