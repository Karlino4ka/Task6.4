package org.example.models.domain

data class User(
    val id: Int? = null,
    val username: String,
    val passwordHash: String,
    val role: String = "user"
)

