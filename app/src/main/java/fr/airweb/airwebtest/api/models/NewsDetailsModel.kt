package fr.airweb.airwebtest.api.models

import com.google.gson.annotations.SerializedName

data class NewsDetailsModel(
    @SerializedName("nid") val nid: Int,
    @SerializedName("type") val type: NewsDetailsModelTypeEnum? = null,
    @SerializedName("date") val date: String? = null,
    @SerializedName("title") val title: String? = null,
    @SerializedName("picture") val picture: String? = null,
    @SerializedName("content") val content: String? = null,
    @SerializedName("dateFormated") val dateFormated: String? = null
)
enum class NewsDetailsModelTypeEnum {
    @SerializedName("news")
    NEWS,
    @SerializedName("actualité")
    ACTUALITE,
    @SerializedName("hot")
    HOT
}