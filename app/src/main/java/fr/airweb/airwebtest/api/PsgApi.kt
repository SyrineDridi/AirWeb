package fr.airweb.airwebtest.api

import fr.airweb.airwebtest.api.models.NewsDetailsModel
import fr.airweb.airwebtest.api.models.NewsModel
import io.reactivex.Flowable
import io.reactivex.Observable
import retrofit2.http.GET


interface PsgApi {

    @GET("psg.json")
    fun getListNews(): Observable<NewsModel>
}