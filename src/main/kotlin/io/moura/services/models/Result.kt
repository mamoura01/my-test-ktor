package io.moura.services.models

import io.ktor.http.*

sealed interface MyResult<out T>

data class Success<out T>(val data: T): MyResult<T>
data class Error(val message: String, val status: HttpStatusCode): MyResult<Nothing>