package io.moura.dao

import io.moura.dao.models.Player

interface PlayerDao {

    suspend fun findPlayers(): List<Player>
    suspend fun findPlayer(pseudo: String): Player?
    suspend fun insertPlayer(pseudo: String): Boolean
    suspend fun updatePlayer(pseudo: String, scoreInc: Int): Boolean
    suspend fun deletePlayers(): Unit
}