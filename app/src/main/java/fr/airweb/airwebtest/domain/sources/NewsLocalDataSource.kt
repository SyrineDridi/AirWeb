package fr.airweb.airwebtest.domain.sources

import fr.airweb.airwebtest.domain.models.NewsDetails
import fr.airweb.airwebtest.domain.models.NewsDetailsTypeEnum
import io.reactivex.Completable
import io.reactivex.Observable


interface NewsLocalDataSource {

    fun getListNews(): Observable<List<NewsDetails>>

    fun getNewsByType(type: NewsDetailsTypeEnum): Observable<List<NewsDetails>>

    fun saveNews(news: List<NewsDetails>) : Completable
}