package fr.airweb.airwebtest.domain.repositories

import fr.airweb.airwebtest.domain.models.News
import fr.airweb.airwebtest.domain.models.PsgModel
import fr.airweb.airwebtest.domain.models.PsgModelTypeEnum
import io.reactivex.Completable
import io.reactivex.Observable

interface NewsRepository {
    fun getPsgNews(): Observable<News>
    fun saveNews(news: List<PsgModel>) :Completable
    fun getPsgNewsFromLocal(): Observable<List<PsgModel>>
    fun getPsgNewsByType(type: PsgModelTypeEnum): Observable<List<PsgModel>>
}