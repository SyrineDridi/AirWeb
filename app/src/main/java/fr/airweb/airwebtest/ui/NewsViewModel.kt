package fr.airweb.airwebtest.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import fr.airweb.airwebtest.domain.models.PsgModel
import fr.airweb.airwebtest.domain.models.PsgModelTypeEnum
import fr.airweb.airwebtest.domain.usescases.FetchPsgNewsByTypeFromLocal
import fr.airweb.airwebtest.domain.usescases.FetchPsgNewsFromLocal
import fr.airweb.airwebtest.domain.usescases.FetchPsgNewsFromRemote
import fr.airweb.airwebtest.domain.usescases.SavePsgNewsInLocal
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*

class NewsViewModel(
    private val fetchPsgNews: FetchPsgNewsFromRemote,
    private val fetchPsgNewsByTypeFromLocal: FetchPsgNewsByTypeFromLocal,
    private val savePsgNewsInLocal: SavePsgNewsInLocal,
    private val fetchPsgNewsFromLocal: FetchPsgNewsFromLocal
) : ViewModel() {
    private val disposables = CompositeDisposable()
    private val list = mutableListOf<PsgModel>()
    override fun onCleared() {
        disposables.clear()
    }

    private val _uiNewsEvent = MutableLiveData<UiNewsEvent>()
    val uiNewsEvent: LiveData<UiNewsEvent>
        get() = _uiNewsEvent

    init {
        fetchNewsLocally()
    }

    private fun fetchNewsLocally() {
        fetchPsgNewsFromLocal.invoke()
            .observeOn(Schedulers.io())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { result ->
                if (result.isNullOrEmpty()) {
                    fetchListNew()
                } else {
                    _uiNewsEvent.value = UiNewsEvent.DisplayAllNews(result)
                    list.addAll(result)
                }
            }
            .also { disposables.add(it) }
    }

    private fun saveData(news: List<PsgModel>) {
        savePsgNewsInLocal.invoke(news).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
            }, { exception ->
                Log.e("error insert", exception.toString())
            }).also {
                disposables.add(it)
            }
    }

    private fun fetchListNew() {
        fetchPsgNews.invoke()
            .observeOn(Schedulers.io())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { result ->
                _uiNewsEvent.value = UiNewsEvent.DisplayNewsByType(result.news)
                saveData(result.news)
            }
            .also { disposables.add(it) }
    }

    fun fetchByType(typeEnum: PsgModelTypeEnum) {
        fetchPsgNewsByTypeFromLocal.invoke(typeEnum)
            .observeOn(Schedulers.io())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { result -> _uiNewsEvent.value = UiNewsEvent.DisplayNewsByType(result) }
            .also { disposables.add(it) }
    }

    fun sortListByTitle() {
        _uiNewsEvent.value = UiNewsEvent.DisplayNewsSortedByTitle(list.sortedBy { t -> t.title })
    }

    fun sortListByDate() {
        _uiNewsEvent.value =
            UiNewsEvent.DisplayNewsSortedByTitle(list.sortedBy { t -> t.date })
    }

    fun searchNewsByTitle(text: String) {
        _uiNewsEvent.value =
            UiNewsEvent.DisplayNewsSearchedByTitle(list.filter { it.title!!.toLowerCase(Locale.ROOT).contains(text.toLowerCase(
                Locale.ROOT
            )
            )
            })
    }
}