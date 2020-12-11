package fr.airweb.airwebtest.domain.usescases

import fr.airweb.airwebtest.domain.models.PsgModel
import fr.airweb.airwebtest.domain.repositories.PsgRepository


class SavePsgNewsInLocal(private val psgRepository: PsgRepository) {
    fun invoke(news :List<PsgModel>) = psgRepository.saveNews(news)
}