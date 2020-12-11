package fr.airweb.airwebtest.domain.sources

import fr.airweb.airwebtest.domain.models.News
import io.reactivex.Observable

interface PsgRemoteDataSource {
    fun getPsgNews(): Observable<News>
}