package fr.airweb.airwebtest.data.mappers

import fr.airweb.airwebtest.db.entities.NewsEntity
import fr.airweb.airwebtest.domain.models.PsgModel
import fr.airweb.airwebtest.domain.models.PsgModelTypeEnum

class NewsEntityMapper {

    fun mapFromEntity(news: NewsEntity): PsgModel =
        PsgModel(
            news.nid,
            news.type?.let { mapStringToENUM(it) },
            news.date,
            news.title,
            news.picture,
            news.content,
            news.dateFormated
        )

    fun mapToEntity(news: PsgModel): NewsEntity? =
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

    fun mapEnumToString(typeEnum: PsgModelTypeEnum): String {
        return when (typeEnum) {
            PsgModelTypeEnum.ACTUALITE -> "ACTUALITE"
            PsgModelTypeEnum.NEWS -> "NEWS"
            PsgModelTypeEnum.HOT -> "HOT"
        }
    }

    fun mapStringToENUM(type: String): PsgModelTypeEnum? {
        return when (type) {
            "ACTUALITE" -> PsgModelTypeEnum.ACTUALITE
            "NEWS" -> PsgModelTypeEnum.NEWS
            "HOT" -> PsgModelTypeEnum.HOT
            else -> null
        }
    }

}