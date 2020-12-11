package fr.airweb.airwebtest.domain.usescases

import fr.airweb.airwebtest.domain.models.PsgModel
import fr.airweb.airwebtest.domain.models.PsgModelTypeEnum
import fr.airweb.airwebtest.domain.repositories.PsgRepository
import io.reactivex.Observable

class FetchPsgNewsByTypeFromLocal(private val psgRepository: PsgRepository) {
    fun invoke(psgModelTypeEnum: PsgModelTypeEnum): Observable<List<PsgModel>> =
        psgRepository.getPsgNewsByType(psgModelTypeEnum)
}