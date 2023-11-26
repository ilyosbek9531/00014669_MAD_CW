package com.example.a00014669_mad_cw.data.dataClasses

data class Parfume(
    val id: String = "",
    val name: String,
    val description: String?,
    val actors: List<String>? = emptyList(),
    val budget: Int? = null,
    val rating: Double? = null,
    val releaseDate: String? = null
)
