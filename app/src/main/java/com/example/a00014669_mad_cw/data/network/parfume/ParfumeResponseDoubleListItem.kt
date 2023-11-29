package com.example.a00014669_mad_cw.data.network.parfume

import com.google.gson.annotations.SerializedName

data class ParfumeResponseDoubleListItem(
    @SerializedName("id")
    val actorEntryId: String,
    @SerializedName("record_id")
    val parfumeRecordId: String,
    @SerializedName("value")
    val value: String
)