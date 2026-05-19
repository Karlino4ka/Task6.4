package org.example.repositories

import org.example.models.domain.User

interface UserRepository {
    suspend fun getUserByUsername(username: String): User?
    suspend fun getUserById(id: Int): User?
    suspend fun createUser(username: String, passwordHash: String): User?
    suspend fun validateCredentials(username: String, password: String): Boolean
}

