package io.moura

import configureRouting
import configureSerialization
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.moura.plugins.configureKoin


fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
    TODO("l'appli d'admin angular")
}

fun Application.module() {
    configureKoin()
    configureRouting()
    configureSerialization()
}
