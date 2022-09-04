package com.xinto.opencord.domain.mapper

import androidx.compose.ui.graphics.Color
import com.xinto.opencord.domain.model.*
import com.xinto.opencord.rest.dto.*
import com.xinto.opencord.rest.service.DiscordCdnServiceImpl
import com.xinto.partialgen.mapToPartial
import kotlinx.datetime.Instant

fun ApiAttachment.toDomain(): DomainAttachment {
    return if (contentType.isNotEmpty()) {
        when (contentType) {
            "video/mp4" -> DomainAttachment.Video(
                id = id.value,
                filename = filename,
                size = size,
                url = url,
                proxyUrl = proxyUrl,
                width = width ?: 100,
                height = height ?: 100
            )
            else -> DomainAttachment.Picture(
                id = id.value,
                filename = filename,
                size = size,
                url = url,
                proxyUrl = proxyUrl,
                width = width ?: 100,
                height = height ?: 100
            )
        }
    } else {
        DomainAttachment.File(
            id = id.value,
            filename = filename,
            size = size,
            url = url,
            proxyUrl = proxyUrl,
        )
    }
}

fun ApiChannel.toDomain(): DomainChannel {
    val permissions = permissions.toDomain()
    return when (type) {
        2 -> DomainChannel.VoiceChannel(
            id = id.value,
            guildId = guildId?.value,
            name = name,
            position = position,
            parentId = parentId?.value,
            permissions = permissions
        )
        4 -> DomainChannel.Category(
            id = id.value,
            guildId = guildId?.value,
            name = name,
            position = position,
            permissions = permissions
        )
        5 -> DomainChannel.AnnouncementChannel(
            id = id.value,
            guildId = guildId?.value,
            name = name,
            position = position,
            parentId = parentId?.value,
            permissions = permissions,
            nsfw = nsfw
        )
        else -> DomainChannel.TextChannel(
            id = id.value,
            guildId = guildId?.value,
            name = name,
            position = position,
            parentId = parentId?.value,
            permissions = permissions,
            nsfw = nsfw
        )
    }
}

fun ApiGuild.toDomain(): DomainGuild {
    val iconUrl = icon?.let { icon ->
        DiscordCdnServiceImpl.getGuildIconUrl(id.toString(), icon)
    }
    val bannerUrl = banner?.let { banner ->
        DiscordCdnServiceImpl.getGuildBannerUrl(id.toString(), banner)
    }
    return DomainGuild(
        id = id.value,
        name = name,
        iconUrl = iconUrl,
        bannerUrl = bannerUrl,
        permissions = permissions.toDomain(),
        premiumTier = premiumTier,
        premiumSubscriptionCount = premiumSubscriptionCount ?: 0,
    )
}

fun ApiGuildMember.toDomain(): DomainGuildMember {
    val avatarUrl = user?.let { user ->
        avatar?.let { avatar ->
            DiscordCdnServiceImpl.getUserAvatarUrl(user.id.toString(), avatar)
        } ?: DiscordCdnServiceImpl.getDefaultAvatarUrl(user.discriminator.toInt().rem(5))
    }
    val domainUser = user?.toDomain()
    return DomainGuildMember(
        user = domainUser,
        nick = nick,
        avatarUrl = avatarUrl
    )
}

fun ApiGuildMemberChunk.toDomain(): DomainGuildMemberChunk {
    val domainMembers = members.map { it.toDomain() }
    return DomainGuildMemberChunk(
        guildId = guildId.value,
        guildMembers = domainMembers,
        chunkIndex = chunkIndex,
        chunkCount = chunkCount,
    )
}

