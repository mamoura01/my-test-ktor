package io.moura.routes.models

import kotlinx.serialization.Serializable

@Serializable
data class Request(
    val pseudo: String
)
