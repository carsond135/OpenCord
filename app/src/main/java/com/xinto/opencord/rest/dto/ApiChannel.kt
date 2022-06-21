package com.xinto.opencord.rest.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiChannel(
    @SerialName("id")
    val id: String,

    @SerialName("name")
    val filename: String,

    @SerialName("size")
    val size: Int,

    @SerialName("url")
    val url: String,

    @SerialName("proxy_url")
    val proxyUrl: String,

    @SerialName("width")
    val width: Int?,

    @SerialName("height")
    val height: Int?,

    @SerialName("content_type")
    val contentType: String = ""
)