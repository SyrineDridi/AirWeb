package fr.airweb.airwebtest.domain.usescases

import fr.airweb.airwebtest.domain.models.PsgModel
import fr.airweb.airwebtest.domain.repositories.NewsRepository


class SavePsgNewsInLocal(private val psgRepository: NewsRepository) {
    fun invoke(news :List<PsgModel>) = psgRepository.saveNews(news)
}