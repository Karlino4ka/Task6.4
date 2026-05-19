package org.example.models.domain

data class Laureate(
    val id: Int? = null,
    val prizeId: Int? = null,
    val fullName: String,
    val portion: String,
    val motivation: String,
    val portraitUrl: String? = null
)

