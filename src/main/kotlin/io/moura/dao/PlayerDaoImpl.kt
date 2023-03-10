package io.moura.dao

import io.moura.dao.models.Player
import org.litote.kmongo.eq

class PlayerDaoImpl(
    private val database: Database
): PlayerDao {
    private val playerCollection = database.playerCollection()

    override suspend fun findPlayers(): List<Player> {
        return playerCollection.find().toList()
    }

    override suspend fun findPlayer(pseudo: String): Player? {
        return playerCollection.findOne(Player::pseudo eq pseudo)
    }

    override suspend fun insertPlayer(pseudo: String): Boolean {
        return playerCollection.insertOne(
            Player(
                pseudo = pseudo
            )
        ).wasAcknowledged()
    }

    override suspend fun updatePlayer(pseudo: String, scoreInc: Int): Boolean {
        val findOneAndUpdate = playerCollection.findOneAndUpdate(
            filter = "{pseudo:\"$pseudo\"}",
            update = "{\$inc:{score:${scoreInc}}}"
        )
        return findOneAndUpdate != null
    }

    override suspend fun deletePlayer(pseudo: String): Boolean {
        return playerCollection.deleteOne(
            Player::pseudo eq pseudo
        ).wasAcknowledged()
    }
}