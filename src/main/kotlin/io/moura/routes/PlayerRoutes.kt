package io.moura.routes

import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.moura.routes.models.Request
import io.moura.services.PlayerService
import io.moura.services.models.Error
import io.moura.services.models.Success

fun Route.playerRouting(
    playerService: PlayerService
) {
    route("/player") {
        get {
            when (val result = playerService.findPlayers()) {
                is Success -> call.respond(result.data)
                is Error -> call.respondText(text = result.message, status = result.status)
            }
        }
        get("{pseudo?}") {
            when (val result = playerService.findPlayer(call.parameters["pseudo"])) {
                is Success -> call.respond(result.data)
                is Error -> call.respondText(text = result.message, status = result.status)
            }
        }
        post {
            val (message, status) = playerService.addPlayer(call.receive<Request>())
            call.respondText(text = message, status = status)
        }
        put {
            val request = call.receive<Request>()
            val (message, status) = playerService.addOnePointToPlayer(request)
            call.respondText(text = message, status = status)
        }
        delete {
            val (message, status) = playerService.deleteAllPlayers()
            call.respondText(text = message, status = status)
        }
    }
}