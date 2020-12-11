package fr.airweb.airwebtest.domain.models

import com.google.gson.annotations.SerializedName

data class NewsModelEntity(
    @SerializedName("news") val news: List<PsgModel>
)