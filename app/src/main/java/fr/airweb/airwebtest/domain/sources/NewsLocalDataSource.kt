package fr.airweb.airwebtest.domain.sources

import fr.airweb.airwebtest.domain.models.PsgModel
import fr.airweb.airwebtest.domain.models.PsgModelTypeEnum
import io.reactivex.Completable
import io.reactivex.Observable


interface NewsLocalDataSource {

    fun getListNews(): Observable<List<PsgModel>>

    fun getNewsByType(type: PsgModelTypeEnum): Observable<List<PsgModel>>

    fun saveNews(news: List<PsgModel>) : Completable
}