package org.example.presentation

import io.ktor.http.*
import org.example.di.AppModule
import org.example.models.dto.PrizeListResponse
import org.example.models.dto.LaureatListResponse
import org.example.models.dto.ErrorResponseDto

class PrizeController {
    suspend fun getAllPrizes() = with(AppModule.prizeService) {
        val prizes = getAllPrizes()
        Pair(HttpStatusCode.OK, PrizeListResponse(prizes))
    }

    suspend fun getPrizeById(id: Int) = with(AppModule.prizeService) {
        val prize = getPrizeById(id)
        if (prize != null) {
            Pair(HttpStatusCode.OK, prize)
        } else {
            Pair(HttpStatusCode.NotFound, ErrorResponseDto("NOT_FOUND", "Prize not found"))
        }
    }

    suspend fun getPrizeByYearAndCategory(year: Int, category: String) = with(AppModule.prizeService) {
        val prize = getPrizeByYearAndCategory(year, category)
        if (prize != null) {
            Pair(HttpStatusCode.OK, prize)
        } else {
            Pair(HttpStatusCode.NotFound, ErrorResponseDto("NOT_FOUND", "Prize not found"))
        }
    }

    suspend fun getLaureateBs(year: Int, category: String) = with(AppModule.prizeService) {
        val laureates = getLaureateBs(year, category)
        if (laureates != null) {
            Pair(HttpStatusCode.OK, LaureatListResponse(laureates))
        } else {
            Pair(HttpStatusCode.NotFound, ErrorResponseDto("NOT_FOUND", "Prize not found"))
        }
    }

    suspend fun addFavoritePrize(userId: Int, prizeId: Int) = with(AppModule.prizeService) {
        val success = addFavoritePrize(userId, prizeId)
        if (success) {
            Pair(HttpStatusCode.OK, mapOf("message" to "Prize added to favorites"))
        } else {
            Pair(HttpStatusCode.BadRequest, ErrorResponseDto("BAD_REQUEST", "Could not add prize to favorites"))
        }
    }

    suspend fun removeFavoritePrize(userId: Int, prizeId: Int) = with(AppModule.prizeService) {
        val success = removeFavoritePrize(userId, prizeId)
        if (success) {
            Pair(HttpStatusCode.OK, mapOf("message" to "Prize removed from favorites"))
        } else {
            Pair(HttpStatusCode.BadRequest, ErrorResponseDto("BAD_REQUEST", "Could not remove prize from favorites"))
        }
    }

    suspend fun getUserFavoritePrizes(userId: Int) = with(AppModule.prizeService) {
        val prizes = getUserFavoritePrizes(userId)
        Pair(HttpStatusCode.OK, PrizeListResponse(prizes))
    }
}
