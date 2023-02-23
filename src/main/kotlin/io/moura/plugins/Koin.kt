package io.moura.plugins

import io.ktor.server.application.*
import io.moura.dao.Database
import io.moura.dao.PlayerDao
import io.moura.dao.PlayerDaoImpl
import io.moura.services.PlayerService
import org.koin.dsl.module
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger

fun Application.configureKoin() {
    install(Koin) {
        slf4jLogger()
        modules(playerModule)
    }
}

val playerModule = module {

    single { Database() }
    single<PlayerDao> { PlayerDaoImpl(get()) }
    single { PlayerService(get()) }
}
