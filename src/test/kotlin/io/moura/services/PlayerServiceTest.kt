package io.moura.services

import io.mockk.coEvery
import io.mockk.mockk
import io.moura.dao.PlayerDao
import io.moura.dao.models.Player
import io.moura.services.models.Error
import io.moura.services.models.Success
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class PlayerServiceTest {

    private lateinit var playerService: PlayerService
    private val mockDao = mockk<PlayerDao>()

    @BeforeEach
    fun setUp() {
        playerService = PlayerService(playerDao = mockDao)
    }

    @Test
    fun `should return an error object when players is empty`() {
        coEvery { mockDao.findPlayers() } returns emptyList()

        runBlocking {
            assertTrue(playerService.findPlayers() is Error)
        }
    }

    @Test
    fun `should return an ordered list of players`() {
        coEvery { mockDao.findPlayers() } returns listOf(
            Player(
                pseudo = "paul"
            ),
            Player(
                pseudo = "pierre",
                score = 10
            )
        )

        runBlocking {
            when (val result = playerService.findPlayers()) {
                is Error -> assert(false)
                is Success -> {
                    assertEquals(2, result.data.size)
                    assertEquals("pierre", result.data.first().pseudo)
                }
            }
        }
    }

    @Test
    fun `should return an error object when searching with null`() {
        runBlocking {
            assertTrue(playerService.findPlayer(pseudo = null) is Error)
        }
    }

    @Test
    fun `should return an error object when searching with unknown pseudo`() {
        coEvery { mockDao.findPlayer(any()) } returns null

        runBlocking {
            assertTrue(playerService.findPlayer(pseudo = "test") is Error)
        }
    }

    @Test
    fun `should return an result object when searching with known pseudo`() {
        coEvery { mockDao.findPlayer(any()) } returns Player(
            pseudo = "pierre",
            score = 10
        )

        runBlocking {
            assertTrue(playerService.findPlayer(pseudo = "pierre") is Success)
        }
    }

 /*   @Test
    fun addPlayer() {
    }

    @Test
    fun addOnePointToPlayer() {
    }

    @Test
    fun deleteAllPlayers() {
    }*/
}