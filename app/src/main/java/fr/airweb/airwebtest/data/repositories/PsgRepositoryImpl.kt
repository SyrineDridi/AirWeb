package fr.airweb.airwebtest.data.repositories

import fr.airweb.airwebtest.domain.models.NewsModelEntity
import fr.airweb.airwebtest.domain.models.PsgModel
import fr.airweb.airwebtest.domain.models.PsgModelTypeEnum
import fr.airweb.airwebtest.domain.repositories.PsgRepository
import fr.airweb.airwebtest.domain.sources.NewsLocalDataSource
import fr.airweb.airwebtest.domain.sources.PsgRemoteDataSource
import io.reactivex.Completable
import io.reactivex.Observable

class PsgRepositoryImpl(
    private val localeSource: NewsLocalDataSource,
    private val remoteSource: PsgRemoteDataSource
) : PsgRepository {

    override fun getPsgNews(): Observable<NewsModelEntity> {
        return remoteSource.getPsgNews()
    }

    override fun saveNews(news: List<PsgModel>) :Completable{
       return localeSource.saveNews(news)
    }

    override fun getPsgNewsFromLocal(): Observable<List<PsgModel>> {
        return localeSource.getListNews()
    }

    override fun getPsgNewsByType(type: PsgModelTypeEnum): Observable<List<PsgModel>> {
        return localeSource.getNewsByType(type)
    }
}