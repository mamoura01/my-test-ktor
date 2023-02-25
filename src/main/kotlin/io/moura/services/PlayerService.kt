package io.moura.services

import io.ktor.http.*
import io.moura.dao.PlayerDao
import io.moura.dao.models.Player
import io.moura.routes.models.Request
import io.moura.services.models.Error
import io.moura.services.models.MyResult
import io.moura.services.models.Success

class PlayerService (
    private val playerDao: PlayerDao
) {
    suspend fun findPlayers(): MyResult<List<Player>> {
        val players = playerDao.findPlayers().sortedByDescending { it.score }

        return if (players.isEmpty()) Error("No player found", HttpStatusCode.OK)
        else Success(players)
    }

    suspend fun findPlayer(pseudo: String?): MyResult<Player> {
        val key = pseudo
            ?: return Error(message = "Missing id", HttpStatusCode.BadRequest)

        val player = playerDao.findPlayer(pseudo = key)
            ?: return Error(message = "No player with pseudo $key", HttpStatusCode.NotFound)

        return Success(player)
    }

    suspend fun addPlayer(request: Request): Pair<String, HttpStatusCode> {
        val success = playerDao.insertPlayer(
                pseudo = request.pseudo
        )

        return if (success) "Player stored correctly" to HttpStatusCode.Created
        else "Player unsuccessfully inserted" to HttpStatusCode.NotModified
    }

    suspend fun addOnePointToPlayer(request: Request): Pair<String, HttpStatusCode> {
        playerDao.updatePlayer(
            pseudo = request.pseudo,
            scoreInc = 1
        )

        return "Player correctly updated" to HttpStatusCode.Accepted
    }

    suspend fun deleteAllPlayers(): Pair<String, HttpStatusCode> {
        val players = playerDao.findPlayers()
        players.forEach {
            playerDao.deletePlayer(pseudo = it.pseudo)
        }

        return "players removed correctly" to HttpStatusCode.Accepted
    }
}