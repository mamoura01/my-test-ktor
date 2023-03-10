package io.moura.dao

import io.moura.dao.models.Player
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo

class Database {

    private val client = KMongo.createClient(
        //ConnectionString("mongodb://127.0.0.1:27017")
    ).coroutine

    private fun playerDatabase() = client.getDatabase(PLAYERS_DATABASE)

    fun playerCollection() = playerDatabase().getCollection<Player>()

    companion object {
        const val PLAYERS_DATABASE = "players"
    }
}