package fr.airweb.airwebtest.data.mappers

import fr.airweb.airwebtest.api.models.NewsDetailsModel
import fr.airweb.airwebtest.api.models.NewsDetailsModelTypeEnum
import fr.airweb.airwebtest.api.models.NewsModel
import fr.airweb.airwebtest.domain.models.News
import fr.airweb.airwebtest.domain.models.NewsDetails
import fr.airweb.airwebtest.domain.models.NewsDetailsTypeEnum

class NewsModelMapper {

    fun mapPsgModelToEntityModel(newsModel: NewsDetailsModel): NewsDetails {
        return NewsDetails(
            newsModel.nid,
            newsModel.type?.let { mapEnumNewsModel(it) },
            newsModel.date,
            newsModel.title,
            newsModel.picture,
            newsModel.content,
            newsModel.dateFormated
        )
    }

    fun mapEnumNewsModel(typeEnum: NewsDetailsModelTypeEnum) : NewsDetailsTypeEnum{
       return when(typeEnum){
           NewsDetailsModelTypeEnum.ACTUALITE -> NewsDetailsTypeEnum.ACTUALITE
           NewsDetailsModelTypeEnum.HOT -> NewsDetailsTypeEnum.HOT
           NewsDetailsModelTypeEnum.NEWS ->NewsDetailsTypeEnum.NEWS
       }
    }

    fun mapPsgModelToEntityNewModel(newsModel: NewsModel): News {
        return News(
               getList(newsModel)
        )
    }

    fun getList(newsModel: NewsModel): List<NewsDetails> {
        val list = mutableListOf<NewsDetails>()
        newsModel.news.forEach { t -> list.add(mapPsgModelToEntityModel(t)) }
        return list
    }
}