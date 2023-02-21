import io.ktor.server.application.*
import io.ktor.server.routing.*
import io.moura.routes.playerRouting
import io.moura.services.PlayerService
import org.koin.ktor.ext.inject

fun Application.configureRouting() {
    routing {
        val playerService by inject<PlayerService>()

        playerRouting(playerService)
    }
}