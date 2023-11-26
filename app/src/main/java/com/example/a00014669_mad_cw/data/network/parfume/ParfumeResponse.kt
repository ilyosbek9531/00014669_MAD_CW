package com.example.a00014669_mad_cw.data.network.parfume

import com.google.gson.annotations.SerializedName

data class ParfumeResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val name: String,
    @SerializedName("description")
    val description: String?,
    @SerializedName("text_list")
    val actors: List<ParfumeResponseActorItem>,
    @SerializedName("size") //the API has no "budget" field
    val budget: Int?,
    @SerializedName("price") //the API has no "rating" field
    val rating: Double?,
    @SerializedName("date")
    val releaseDate: String?
)