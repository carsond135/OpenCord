package com.xinto.opencord.rest.service

import com.xinto.opencord.BuildConfig

interface DiscordCdnService

class DiscordCdnServiceImpl : DiscordCdnService {

    companion object {
        private const val BASE = BuildConfig.URL_CDN
    }
}