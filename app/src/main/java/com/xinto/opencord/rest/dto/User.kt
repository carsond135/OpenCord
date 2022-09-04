package com.xinto.opencord.rest.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiUser(
    @SerialName("id")
    val id: ApiSnowflake,

    @SerialName("username")
    val username: String,

    @SerialName("discriminator")
    val discriminator: String,

    @SerialName("avatar")
    val avatar: String?,

    @SerialName("bot")
    val bot: Boolean = false,

    @SerialName("pronouns")
    val pronouns: String? = null,

    @SerialName("bio")
    val bio: String? = null,

    @SerialName("banner")
    val banner: String? = null,

    @SerialName("accent_color")
    val accentColor: String? = null,

    @SerialName("public_flags")
    val publicFlags: Int? = null,

    @SerialName("flags")
    val privateFlags: Int? = null,

    @SerialName("verified")
    val verified: Boolean? = null,

    @SerialName("email")
    val email: String? = null,

    @SerialName("phone")
    val phone: String? = null,

    @SerialName("mfa_enabled")
    val mfaEnabled: Boolean? = null,

    @SerialName("locale")
    val locale: String? = null,

    @SerialName("purchased_flags")
    val purchasedFlags: Int? = null,

    @SerialName("premium")
    val premium: Boolean? = null,
)