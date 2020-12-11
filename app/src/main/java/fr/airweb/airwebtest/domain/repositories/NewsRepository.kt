package fr.airweb.airwebtest.domain.repositories

import fr.airweb.airwebtest.domain.models.News
import fr.airweb.airwebtest.domain.models.NewsDetails
import fr.airweb.airwebtest.domain.models.NewsDetailsTypeEnum
import io.reactivex.Completable
import io.reactivex.Observable

interface NewsRepository {
    fun getPsgNews(): Observable<News>
    fun saveNews(news: List<NewsDetails>) :Completable
    fun getPsgNewsFromLocal(): Observable<List<NewsDetails>>
    fun getPsgNewsByType(type: NewsDetailsTypeEnum): Observable<List<NewsDetails>>
}