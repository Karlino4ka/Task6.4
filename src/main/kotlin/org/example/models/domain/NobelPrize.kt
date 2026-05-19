package org.example.models.domain

data class NobelPrize(
    val id: Int? = null,
    val year: Int,
    val category: String,
    val fullName: String,
    val motivation: String,
    val detailLink: String,
    val laureates: List<Laureate> = emptyList()
)

