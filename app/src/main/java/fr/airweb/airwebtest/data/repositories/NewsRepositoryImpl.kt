package fr.airweb.airwebtest.data.repositories

import fr.airweb.airwebtest.domain.models.News
import fr.airweb.airwebtest.domain.models.NewsDetails
import fr.airweb.airwebtest.domain.models.NewsDetailsTypeEnum
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

    override fun saveNews(news: List<NewsDetails>) :Completable{
       return localeSource.saveNews(news)
    }

    override fun getPsgNewsFromLocal(): Observable<List<NewsDetails>> {
        return localeSource.getListNews()
    }

    override fun getPsgNewsByType(type: NewsDetailsTypeEnum): Observable<List<NewsDetails>> {
        return localeSource.getNewsByType(type)
    }
}