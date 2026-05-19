package org.example

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import org.example.plugins.*
import org.example.routing.appRouting
import org.example.data.database.DatabaseFactory

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = { appModule() })
        .start(wait = true)
}

fun Application.appModule() {
    // Инициализируем БД
    DatabaseFactory.init()

    installPlugins()
    appRouting()
}

