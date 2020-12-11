package fr.airweb.airwebtest.domain.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class NewsDetails(
    @SerializedName("nid") val nid: Int?,
    @SerializedName("type") val type: NewsDetailsTypeEnum? = null,
    @SerializedName("date") val date: String? = null,
    @SerializedName("title") val title: String?,
    @SerializedName("picture") val picture: String? = null,
    @SerializedName("content") val content: String? = null,
    @SerializedName("dateFormated") val dateFormated: String? = null
) : Serializable

enum class NewsDetailsTypeEnum {
    @SerializedName("news")
    NEWS,

    @SerializedName("actualité")
    ACTUALITE,

    @SerializedName("hot")
    HOT
}