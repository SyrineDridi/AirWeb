package fr.airweb.airwebtest.domain.usescases

import fr.airweb.airwebtest.domain.models.NewsModelEntity
import fr.airweb.airwebtest.domain.models.PsgModel
import fr.airweb.airwebtest.domain.repositories.PsgRepository
import io.reactivex.Flowable
import io.reactivex.Observable


class FetchPsgNewsFromRemote(private val psgRepository: PsgRepository) {
    fun invoke(): Observable<NewsModelEntity> =  psgRepository.getPsgNews()
}