package org.example.routing

import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.routing.*
import io.ktor.server.response.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.request.*
import org.example.presentation.PrizeController
import org.example.models.dto.ErrorResponseDto

fun Routing.prizeRouting() {
    val controller = PrizeController()

    route("/prizes") {
        authenticate("auth-jwt") {
            get {
                val (status, response) = controller.getAllPrizes()
                call.respond(status, response)
            }

            get("/{year}/{category}") {
                val year = call.parameters["year"]?.toIntOrNull()
                val category = call.parameters["category"]

                if (year == null || category == null) {
                    call.respond(
                        HttpStatusCode.BadRequest,
                        ErrorResponseDto("BAD_REQUEST", "Invalid parameters")
                    )
                    return@get
                }

                val (status, response) = controller.getPrizeByYearAndCategory(year, category)
                call.respond(status, response)
            }

            get("/{year}/{category}/laureates") {
                val year = call.parameters["year"]?.toIntOrNull()
                val category = call.parameters["category"]

                if (year == null || category == null) {
                    call.respond(
                        HttpStatusCode.BadRequest,
                        ErrorResponseDto("BAD_REQUEST", "Invalid parameters")
                    )
                    return@get
                }

                val (status, response) = controller.getLaureateBs(year, category)
                call.respond(status, response)
            }
        }
    }
}

