package fr.airweb.airwebtest.domain.sources

import fr.airweb.airwebtest.domain.models.NewsModelEntity
import fr.airweb.airwebtest.domain.models.PsgModel
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single

interface PsgRemoteDataSource {
    fun getPsgNews(): Observable<NewsModelEntity>
}