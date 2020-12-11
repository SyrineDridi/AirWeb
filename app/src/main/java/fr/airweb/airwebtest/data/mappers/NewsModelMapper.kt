package fr.airweb.airwebtest.data.mappers

import fr.airweb.airwebtest.api.models.NewsDetailsModel
import fr.airweb.airwebtest.api.models.NewsDetailsModelTypeEnum
import fr.airweb.airwebtest.api.models.NewsModel
import fr.airweb.airwebtest.domain.models.News
import fr.airweb.airwebtest.domain.models.PsgModel
import fr.airweb.airwebtest.domain.models.PsgModelTypeEnum

class NewsModelMapper {

    fun mapPsgModelToEntityModel(newsModel: NewsDetailsModel): PsgModel {
        return PsgModel(
            newsModel.nid,
            newsModel.type?.let { mapEnumNewsModel(it) },
            newsModel.date,
            newsModel.title,
            newsModel.picture,
            newsModel.content,
            newsModel.dateFormated
        )
    }

    fun mapEnumNewsModel(typeEnum: NewsDetailsModelTypeEnum) : PsgModelTypeEnum{
       return when(typeEnum){
           NewsDetailsModelTypeEnum.ACTUALITE -> PsgModelTypeEnum.ACTUALITE
           NewsDetailsModelTypeEnum.HOT -> PsgModelTypeEnum.HOT
           NewsDetailsModelTypeEnum.NEWS ->PsgModelTypeEnum.NEWS
       }
    }

    fun mapPsgModelToEntityNewModel(newsModel: NewsModel): News {
        return News(
               getList(newsModel)
        )
    }

    fun getList(newsModel: NewsModel): List<PsgModel> {
        val list = mutableListOf<PsgModel>()
        newsModel.news.forEach { t -> list.add(mapPsgModelToEntityModel(t)) }
        return list
    }
}