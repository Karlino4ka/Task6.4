package org.example.services

import org.example.models.domain.NobelPrize
import org.example.models.domain.Laureate
import org.example.models.dto.NobelPrizeResponse
import org.example.models.dto.LaureateResponse
import org.example.repositories.PrizeRepository

interface PrizeService {
    suspend fun getAllPrizes(): List<NobelPrizeResponse>
    suspend fun getPrizeById(id: Int): NobelPrizeResponse?
    suspend fun getPrizeByYearAndCategory(year: Int, category: String): NobelPrizeResponse?
    suspend fun getLaureateBs(year: Int, category: String): List<LaureateResponse>?
    suspend fun addFavoritePrize(userId: Int, prizeId: Int): Boolean
    suspend fun removeFavoritePrize(userId: Int, prizeId: Int): Boolean
    suspend fun getUserFavoritePrizes(userId: Int): List<NobelPrizeResponse>
}

class PrizeServiceImpl(private val prizeRepository: PrizeRepository) : PrizeService {
    override suspend fun getAllPrizes(): List<NobelPrizeResponse> {
        return prizeRepository.getAllPrizes().map { it.toResponse() }
    }

    override suspend fun getPrizeById(id: Int): NobelPrizeResponse? {
        return prizeRepository.getPrizeById(id)?.toResponse()
    }

    override suspend fun getPrizeByYearAndCategory(year: Int, category: String): NobelPrizeResponse? {
        return prizeRepository.getPrizeByYearAndCategory(year, category)?.toResponse()
    }

    override suspend fun getLaureateBs(year: Int, category: String): List<LaureateResponse>? {
        return prizeRepository.getLaureateBs(year, category)?.map { it.toResponse() }
    }

    override suspend fun addFavoritePrize(userId: Int, prizeId: Int): Boolean {
        return prizeRepository.addFavoritePrize(userId, prizeId)
    }

    override suspend fun removeFavoritePrize(userId: Int, prizeId: Int): Boolean {
        return prizeRepository.removeFavoritePrize(userId, prizeId)
    }

    override suspend fun getUserFavoritePrizes(userId: Int): List<NobelPrizeResponse> {
        return prizeRepository.getUserFavoritePrizes(userId).map { it.toResponse() }
    }

    private fun NobelPrize.toResponse() = NobelPrizeResponse(
        id = id ?: 0,
        year = year,
        category = category,
        fullName = fullName,
        motivation = motivation,
        detailLink = detailLink,
        laureates = laureates.map { it.toResponse() }
    )

    private fun Laureate.toResponse() = LaureateResponse(
        id = id ?: 0,
        fullName = fullName,
        portion = portion,
        motivation = motivation,
        portraitUrl = portraitUrl
    )
}

