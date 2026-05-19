package org.example.routing

import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.auth.*
import io.ktor.server.routing.*
import org.example.models.dto.LoginRequest
import org.example.presentation.AuthController

fun Routing.authRouting() {
    val controller = AuthController()

    route("/auth") {
        post("/login") {
            val request = call.receive<LoginRequest>()
            val (status, response) = controller.login(request)
            call.respond(status, response)
        }
    }
}

//curl -X POST http://localhost:8080/auth/login ^
//-H "Content-Type: application/json" ^
//-d "{\"username"\:\"admin"\,\"password"\:\"admin123"\}"