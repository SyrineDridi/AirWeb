package fr.airweb.airwebtest.data.sources

import fr.airweb.airwebtest.data.mappers.NewsEntityMapper
import fr.airweb.airwebtest.db.AppDatabase
import fr.airweb.airwebtest.domain.models.PsgModel
import fr.airweb.airwebtest.domain.models.PsgModelTypeEnum
import fr.airweb.airwebtest.domain.sources.NewsLocalDataSource
import io.reactivex.Completable
import io.reactivex.Observable

class NewsLocalSourceImpl(
    private val mapper: NewsEntityMapper,
    private val db: AppDatabase
) : NewsLocalDataSource {
    override fun getListNews(): Observable<List<PsgModel>> {
        return db.newsDao().getAll().map { list ->
            list.map {
                mapper.mapFromEntity(it)
            }
        }
    }

    override fun getNewsByType(type: PsgModelTypeEnum): Observable<List<PsgModel>> {
        return db.newsDao().getByType(mapper.mapEnumToString(type)).map { list ->
            list.map {
                mapper.mapFromEntity(it)
            }
        }
    }

    override fun saveNews(news: List<PsgModel>) : Completable{
        val newsEntity =  news.map {
            mapper.mapToEntity(it)
        }
       return db.newsDao().insertAll(newsEntity)
    }
}