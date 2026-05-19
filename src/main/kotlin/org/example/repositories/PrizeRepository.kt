package org.example.repositories

import org.example.models.domain.NobelPrize
import org.example.models.domain.Laureate

interface PrizeRepository {
    suspend fun getAllPrizes(): List<NobelPrize>
    suspend fun getPrizeById(id: Int): NobelPrize?
    suspend fun getPrizeByYearAndCategory(year: Int, category: String): NobelPrize?
    suspend fun getLaureateBs(year: Int, category: String): List<Laureate>?
    suspend fun savePrize(prize: NobelPrize): NobelPrize?
    suspend fun addFavoritePrize(userId: Int, prizeId: Int): Boolean
    suspend fun removeFavoritePrize(userId: Int, prizeId: Int): Boolean
    suspend fun getUserFavoritePrizes(userId: Int): List<NobelPrize>
}

