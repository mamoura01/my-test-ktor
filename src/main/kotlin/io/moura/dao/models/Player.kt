package io.moura.dao.models

@kotlinx.serialization.Serializable
data class Player(
    val pseudo: String,
    val score: Long = 0
)