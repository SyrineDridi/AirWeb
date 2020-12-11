package fr.airweb.airwebtest.domain.usescases

import fr.airweb.airwebtest.domain.models.NewsDetails

import fr.airweb.airwebtest.domain.repositories.NewsRepository
import io.reactivex.Observable

class FetchPsgNewsFromLocal(private val psgRepository: NewsRepository) {
    fun invoke(): Observable<List<NewsDetails>> =
        psgRepository.getPsgNewsFromLocal()
}