package fr.airweb.airwebtest.data.mappers

import fr.airweb.airwebtest.db.entities.NewsEntity
import fr.airweb.airwebtest.domain.models.NewsDetails
import fr.airweb.airwebtest.domain.models.NewsDetailsTypeEnum

class NewsEntityMapper {

    fun mapFromEntity(news: NewsEntity): NewsDetails =
        NewsDetails(
            news.nid,
            news.type?.let { mapStringToENUM(it) },
            news.date,
            news.title,
            news.picture,
            news.content,
            news.dateFormated
        )

    fun mapToEntity(news: NewsDetails): NewsEntity? =
        news.nid?.let {
            NewsEntity(
                it,
                news.type?.let { it1 -> mapEnumToString(it1) },
                news.date,
                news.title,
                news.picture,
                news.content,
                news.dateFormated
            )
        }

    fun mapEnumToString(typeEnum: NewsDetailsTypeEnum): String {
        return when (typeEnum) {
            NewsDetailsTypeEnum.ACTUALITE -> "ACTUALITE"
            NewsDetailsTypeEnum.NEWS -> "NEWS"
            NewsDetailsTypeEnum.HOT -> "HOT"
        }
    }

    fun mapStringToENUM(type: String): NewsDetailsTypeEnum? {
        return when (type) {
            "ACTUALITE" -> NewsDetailsTypeEnum.ACTUALITE
            "NEWS" -> NewsDetailsTypeEnum.NEWS
            "HOT" -> NewsDetailsTypeEnum.HOT
            else -> null
        }
    }

}