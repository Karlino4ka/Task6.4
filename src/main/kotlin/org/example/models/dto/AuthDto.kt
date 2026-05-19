package org.example.models.dto

import kotlinx.serialization.Serializable

@Serializable
data class LoginRequest(
    val username: String,
    val password: String
)

@Serializable
data class RegisterRequest(
    val username: String,
    val password: String
)

@Serializable
data class LoginResponse(
    val token: String,
    val expiresIn: Long
)

@Serializable
data class ErrorResponse(
    val error: String,
    val message: String
)

@Serializable
data class ErrorResponseDto(
    val error: String,
    val message: String
)

