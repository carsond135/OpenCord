package com.xinto.opencord.domain.mapper

import com.xinto.opencord.domain.model.*
import com.xinto.opencord.rest.dto.*
import com.xinto.partialgen.mapToPartial

fun DomainUserSettingsPartial.toApi(): ApiUserSettingsPartial {
    val apiPartialTheme = theme.mapToPartial { it.value }
    val apiPartialGuildPositions = guildPositions.mapToPartial { guildPositions ->
        guildPositions.map { ApiSnowflake(it) }
    }
    val apiPartialStatus = status.mapToPartial { it.value }
    val apiPartialFriendSourceFlags = friendSourceFlags.mapToPartial { it.toApi() }
    val apiPartialGuildFolders = guildFolders.mapToPartial { guildFolders ->
        guildFolders.map { it.toApi() }
    }
    val apiPartialCustomStatus = customStatus.mapToPartial { it?.toApi() }
    return ApiUserSettingsPartial(
        locale = locale,
        showCurrentGame = showCurrentGame,
        inlineAttachmentMedia = inlineAttachmentMedia,
        
    )
}