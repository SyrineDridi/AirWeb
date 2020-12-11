package fr.airweb.airwebtest.domain.repositories

import fr.airweb.airwebtest.domain.models.NewsModelEntity
import fr.airweb.airwebtest.domain.models.PsgModel
import fr.airweb.airwebtest.domain.models.PsgModelTypeEnum
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single

interface PsgRepository {
    fun getPsgNews(): Observable<NewsModelEntity>
    fun saveNews(news: List<PsgModel>) :Completable
    fun getPsgNewsFromLocal(): Observable<List<PsgModel>>
    fun getPsgNewsByType(type: PsgModelTypeEnum): Observable<List<PsgModel>>
}