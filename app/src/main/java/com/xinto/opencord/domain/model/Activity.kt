package com.xinto.opencord.domain.model

import com.xinto.enumgetter.GetterGen

@GetterGen
enum class ActivityType(val value: Int) {
    Game(0),
    Streaming(1),
    Listening(2),
    Watching(3),
    Custom(4),
    Competing(5),
    Unknown(-1);

    companion object
}

sealed interface DomainActivity {
    val name: String
    val createdAt: Long
    val type: ActivityType
}

data class DomainActivityGame(
    override val name: String,
    override val createdAt: Long,
    val id: String?,
    val state: String,
    val details: String,
    val applicationId: Long,
    val party: DomainActivityParty?,
    val assets: DomainActivityAssets?,
    val secrets: DomainActivitySecrets?,
    val timestamps: DomainActivityTimestamp?,
) : DomainActivity {
    override val type: ActivityType.Game
}

data class DomainActivityStreaming(
    override val name: String,
    override val createdAt: Long,
    val id: String,
    val url: String,
    val state: String,
    val details: String,
    val assets: DomainActivityAssets,
) : DomainActivity {
    override val type: ActivityType.Streaming
}

data class DomainActivityListening(
    override val name: String,j
    override val createdAt: Long,
    val id: String?,
    val state: String,
    val details: String,
    val applicationId: Long,
    val party: DomainActivityParty?,
    val assets: DomainActivityAssets?,
    val secrets: DomainActivitySecrets?,
    val timestamps: DomainActivityTimestamp?,
) : DomainActivity {
    override val type: ActivityType.Game
}