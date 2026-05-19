package org.example.models.dto

import kotlinx.serialization.Serializable

@Serializable
data class LaureateResponse(
    val id: Int,
    val fullName: String,
    val portion: String,
    val motivation: String,
    val portraitUrl: String? = null
)

@Serializable
data class NobelPrizeResponse(
    val id: Int,
    val year: Int,
    val category: String,
    val fullName: String,
    val motivation: String,
    val detailLink: String,
    val laureates: List<LaureateResponse> = emptyList()
)

@Serializable
data class PrizeListResponse(
    val prizes: List<NobelPrizeResponse>
)

@Serializable
data class LaureatListResponse(
    val laureates: List<LaureateResponse>
)


@Serializable
data class UserProfileResponse(
    val id: Int,
    val username: String,
    val role: String
)

@Serializable
data class MessageResponse(
    val message: String
)

