package fr.airweb.airwebtest.domain.usescases

import fr.airweb.airwebtest.domain.models.NewsDetails
import fr.airweb.airwebtest.domain.models.NewsDetailsTypeEnum
import fr.airweb.airwebtest.domain.repositories.NewsRepository
import io.reactivex.Observable

class FetchPsgNewsByTypeFromLocal(private val psgRepository: NewsRepository) {
    fun invoke(newsDetailsTypeEnum: NewsDetailsTypeEnum): Observable<List<NewsDetails>> =
        psgRepository.getPsgNewsByType(newsDetailsTypeEnum)
}