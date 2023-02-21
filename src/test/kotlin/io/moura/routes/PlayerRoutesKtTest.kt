package io.moura.routes;

import configureRouting
import io.ktor.client.request.*
import io.ktor.server.testing.*
import kotlin.test.Test

class PlayerRoutesKtTest {

    @Test
    fun testPutPlayer() = testApplication {
        application {
            configureRouting()
        }
        client.put("/player").apply {
            TODO("Please write your test here")
        }
    }
}