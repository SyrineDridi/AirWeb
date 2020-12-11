package fr.airweb.airwebtest.api.models

import com.google.gson.annotations.SerializedName

data class NewsModel(
    @SerializedName("news") val news: List<NewsDetailsModel>
)