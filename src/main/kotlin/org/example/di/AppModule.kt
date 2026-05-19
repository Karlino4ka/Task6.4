package org.example.di

import org.example.repositories.UserRepository
import org.example.data.repository.UserRepositoryImpl
import org.example.repositories.PrizeRepository
import org.example.data.repository.PrizeRepositoryImpl
import org.example.services.AuthService
import org.example.services.AuthServiceImpl
import org.example.services.PrizeService
import org.example.services.PrizeServiceImpl

object AppModule {
    val userRepository: UserRepository by lazy { UserRepositoryImpl() }
    val prizeRepository: PrizeRepository by lazy { PrizeRepositoryImpl() }

    val authService: AuthService by lazy { AuthServiceImpl(userRepository) }
    val prizeService: PrizeService by lazy { PrizeServiceImpl(prizeRepository) }
}

