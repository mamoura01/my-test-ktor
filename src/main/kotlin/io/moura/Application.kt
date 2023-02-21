package io.moura

import configureRouting
import configureSerialization
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.moura.dao.models.Player
import io.moura.plugins.configureKoin
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
    TODO("init de la bdd")
    TODO("un peu de logs")
    TODO("un peu de TU")
    TODO("un peu de integ/scenar http")
    TODO("l'appli d'admin angular")
}

fun Application.module() {
    configureKoin()
    configureRouting()
    configureSerialization()
}
