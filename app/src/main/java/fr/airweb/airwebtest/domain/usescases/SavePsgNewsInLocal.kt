package fr.airweb.airwebtest.domain.usescases

import fr.airweb.airwebtest.domain.models.NewsDetails
import fr.airweb.airwebtest.domain.repositories.NewsRepository


class SavePsgNewsInLocal(private val psgRepository: NewsRepository) {
    fun invoke(news :List<NewsDetails>) = psgRepository.saveNews(news)
}