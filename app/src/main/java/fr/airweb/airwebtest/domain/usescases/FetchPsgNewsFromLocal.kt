package fr.airweb.airwebtest.domain.usescases

import fr.airweb.airwebtest.domain.models.PsgModel
import fr.airweb.airwebtest.domain.repositories.NewsRepository
import io.reactivex.Observable

class FetchPsgNewsFromLocal(private val psgRepository: NewsRepository) {
    fun invoke(): Observable<List<PsgModel>> =
        psgRepository.getPsgNewsFromLocal()
}