package org.example.plugins

import io.ktor.server.application.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.http.*
import io.ktor.server.response.*
import org.example.models.dto.ErrorResponse

fun Application.installStatusPagesPlugin() {
    install(StatusPages) {
        exception<IllegalArgumentException> { call, cause ->
            call.respond(HttpStatusCode.BadRequest, ErrorResponse("BAD_REQUEST", cause.message ?: "Invalid request"))
        }
        exception<Exception> { call, cause ->
            call.respond(HttpStatusCode.InternalServerError, ErrorResponse("INTERNAL_ERROR", cause.message ?: "Internal server error"))
        }
        status(HttpStatusCode.NotFound) { call, _ ->
            call.respond(HttpStatusCode.NotFound, ErrorResponse("NOT_FOUND", "Resource not found"))
        }
        status(HttpStatusCode.Unauthorized) { call, _ ->
            call.respond(HttpStatusCode.Unauthorized, ErrorResponse("UNAUTHORIZED", "Authentication required"))
        }
    }
}

