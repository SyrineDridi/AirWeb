package fr.airweb.airwebtest.data.sources

import android.util.Log
import fr.airweb.airwebtest.api.PsgApi
import fr.airweb.airwebtest.data.mappers.NewsModelMapper
import fr.airweb.airwebtest.domain.models.News
import fr.airweb.airwebtest.domain.sources.PsgRemoteDataSource
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class NewsRemoteSourceImpl(private val psgApi: PsgApi, private val psgModelMapper: NewsModelMapper) :
    PsgRemoteDataSource {

    override fun getPsgNews(): Observable<News> {
        return psgApi.getListNews().observeOn(Schedulers.io())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { t -> psgModelMapper.mapPsgModelToEntityNewModel(t) }
            .doOnError { throwable -> Log.e("PsgSourceImpl", throwable.toString()) }
    }
}