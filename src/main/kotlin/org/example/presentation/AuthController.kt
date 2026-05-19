package org.example.presentation

import io.ktor.http.*
import org.example.di.AppModule
import org.example.models.dto.LoginRequest
import org.example.models.dto.RegisterRequest
import org.example.models.dto.ErrorResponseDto

class AuthController {
    suspend fun login(request: LoginRequest) = with(AppModule.authService) {
        val response = login(request)
        if (response != null) {
            Pair(HttpStatusCode.OK, response)
        } else {
            Pair(HttpStatusCode.Unauthorized, ErrorResponseDto("UNAUTHORIZED", "Invalid credentials"))
        }
    }

    suspend fun register(request: RegisterRequest) = with(AppModule.authService) {
        val response = register(request)
        if (response != null) {
            Pair(HttpStatusCode.Created, response)
        } else {
            Pair(HttpStatusCode.BadRequest, ErrorResponseDto("CONFLICT", "Username already exists"))
        }
    }
}
