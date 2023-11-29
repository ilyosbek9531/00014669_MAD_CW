package com.example.a00014669_mad_cw.data.network.parfume

import com.google.gson.annotations.SerializedName

data class ParfumeResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("description")
    val description: String?,
    @SerializedName("double_list")
    val volumes: List<ParfumeResponseDoubleListItem>? = null,
    @SerializedName("price")
    val price: Double?,
    @SerializedName("created_at")
    val releaseDate: String?,
    @SerializedName("url")
    val image: String?,
    @SerializedName("type")
    val origin: String?,
    @SerializedName("is_it_true")
    val isItTrue: String?
)
