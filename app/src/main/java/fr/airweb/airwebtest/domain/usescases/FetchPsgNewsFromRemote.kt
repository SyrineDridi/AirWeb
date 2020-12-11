package fr.airweb.airwebtest.domain.usescases

import fr.airweb.airwebtest.domain.models.News
import fr.airweb.airwebtest.domain.repositories.NewsRepository
import io.reactivex.Observable


class FetchPsgNewsFromRemote(private val psgRepository: NewsRepository) {
    fun invoke(): Observable<News> =  psgRepository.getPsgNews()
}