package org.example.plugins

import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import com.auth0.jwt.JWT
import io.ktor.http.HttpStatusCode
import io.ktor.server.response.respond
import org.example.security.JwtConfig

fun Application.installAuthPlugin() {
    authentication {
        jwt("auth-jwt") {
            realm = "nobelprize-api"

            verifier { _ ->
                JWT.require(JwtConfig.algorithm)
                    .withIssuer(JwtConfig.ISSUER)
                    .withAudience(JwtConfig.AUDIENCE)
                    .build()
            }

            validate { credential ->
                val username = credential.payload.getClaim("username").asString()
                if (username != null) {
                    JWTPrincipal(credential.payload)
                } else {
                    null
                }
            }

            challenge { _, _ ->
                call.respond(
                    HttpStatusCode.Unauthorized,
                    mapOf("error" to "Invalid or expired token")
                )
            }
        }
    }
}