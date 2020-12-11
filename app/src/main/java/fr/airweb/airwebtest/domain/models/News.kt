package fr.airweb.airwebtest.domain.models

import com.google.gson.annotations.SerializedName

data class News(
    @SerializedName("news") val news: List<PsgModel>
)