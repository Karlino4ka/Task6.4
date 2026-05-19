package org.example.routing

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.Serializable

@Serializable
data class HealthResponse(
    val status: String,
    val message: String
)

fun Application.appRouting() {
    routing {
        get("/health") {
            call.respond(HealthResponse("OK", "Nobel Prize API is running"))
        }

        authRouting()
        userRouting()
        prizeRouting()
    }
}



