package org.example.services

import org.example.models.dto.LoginRequest
import org.example.models.dto.LoginResponse
import org.example.models.dto.RegisterRequest
import org.example.repositories.UserRepository
import org.example.security.JwtConfig
import org.example.security.PasswordHasher

interface AuthService {
    suspend fun login(request: LoginRequest): LoginResponse?
    suspend fun register(request: RegisterRequest): LoginResponse?
}

class AuthServiceImpl(private val userRepository: UserRepository) : AuthService {
    override suspend fun login(request: LoginRequest): LoginResponse? {
        if (!userRepository.validateCredentials(request.username, request.password)) {
            return null
        }
        val user = userRepository.getUserByUsername(request.username) ?: return null
        val token = JwtConfig.generateToken(request.username, user.id)
        return LoginResponse(token = token, expiresIn = 30 * 60) // 30 minutes in seconds
    }

    override suspend fun register(request: RegisterRequest): LoginResponse? {
        // Проверяем, не существует ли уже такой пользователь
        if (userRepository.getUserByUsername(request.username) != null) {
            return null
        }

        val passwordHash = PasswordHasher.hash(request.password)
        val user = userRepository.createUser(request.username, passwordHash) ?: return null

        val token = JwtConfig.generateToken(user.username, user.id)
        return LoginResponse(token = token, expiresIn = 30 * 60)
    }
}
