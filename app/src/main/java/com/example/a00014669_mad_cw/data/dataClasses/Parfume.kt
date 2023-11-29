package com.example.a00014669_mad_cw.data.dataClasses

import com.example.a00014669_mad_cw.data.network.parfume.ParfumeResponseDoubleListItem

data class Parfume(
    val id: String = "",
    val title: String,
    val description: String?,
    val image: String?,
    val price: Double?,
    val origin: String?,
    val releaseDate: String? = null,
    val volumes: List<ParfumeResponseDoubleListItem>?,
    val isItTrue: String?,
)
