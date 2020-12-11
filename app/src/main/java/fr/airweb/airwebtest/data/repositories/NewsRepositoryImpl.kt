package fr.airweb.airwebtest.data.repositories

import fr.airweb.airwebtest.domain.models.News
import fr.airweb.airwebtest.domain.models.PsgModel
import fr.airweb.airwebtest.domain.models.PsgModelTypeEnum
import fr.airweb.airwebtest.domain.repositories.NewsRepository
import fr.airweb.airwebtest.domain.sources.NewsLocalDataSource
import fr.airweb.airwebtest.domain.sources.PsgRemoteDataSource
import io.reactivex.Completable
import io.reactivex.Observable

class NewsRepositoryImpl(
    private val localeSource: NewsLocalDataSource,
    private val remoteSource: PsgRemoteDataSource
) : NewsRepository {

    override fun getPsgNews(): Observable<News> {
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