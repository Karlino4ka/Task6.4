package org.example.plugins

import io.ktor.server.application.*

fun Application.installPlugins() {
    installContentNegotiationPlugin()
    installCorsPlugin()
    installCallLoggingPlugin()
    installStatusPagesPlugin()
    installAuthPlugin()
}

