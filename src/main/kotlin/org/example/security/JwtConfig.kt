package org.example.security

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.interfaces.DecodedJWT
import java.util.Date

object JwtConfig {
    private const val SECRET = "a-very-long-secret-key-for-jwt-that-is-at-least-32-characters-long"
    const val ISSUER = "nobelprize-api"
    const val AUDIENCE = "nobelprize-users"
    private const val VALIDITY_IN_MS = 30 * 60 * 1000 // 30 минут

    val algorithm: Algorithm = Algorithm.HMAC256(SECRET)

    fun generateToken(username: String, userId: Int? = null): String = JWT.create()
        .withIssuer(ISSUER)
        .withAudience(AUDIENCE)
        .withClaim("username", username)
        .apply {
            if (userId != null) {
                withClaim("userId", userId)
            }
        }
        .withIssuedAt(Date())
        .withExpiresAt(Date(System.currentTimeMillis() + VALIDITY_IN_MS))
        .sign(algorithm)

    fun verifyToken(token: String): DecodedJWT = JWT.require(algorithm)
        .withIssuer(ISSUER)
        .withAudience(AUDIENCE)
        .build()
        .verify(token)
}