package fr.airweb.airwebtest.domain.usescases

import fr.airweb.airwebtest.domain.models.PsgModel
import fr.airweb.airwebtest.domain.repositories.PsgRepository
import io.reactivex.Observable

class FetchPsgNewsFromLocal(private val psgRepository: PsgRepository) {
    fun invoke(): Observable<List<PsgModel>> =
        psgRepository.getPsgNewsFromLocal()
}