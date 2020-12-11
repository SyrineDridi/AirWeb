package fr.airweb.airwebtest.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import fr.airweb.airwebtest.domain.models.NewsDetails
import fr.airweb.airwebtest.domain.models.NewsDetailsTypeEnum
import fr.airweb.airwebtest.domain.usescases.FetchPsgNewsByTypeFromLocal
import fr.airweb.airwebtest.domain.usescases.FetchPsgNewsFromLocal
import fr.airweb.airwebtest.domain.usescases.FetchPsgNewsFromRemote
import fr.airweb.airwebtest.domain.usescases.SavePsgNewsInLocal
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import java.util.*

class NewsViewModel(
    private val fetchPsgNews: FetchPsgNewsFromRemote,
    private val fetchPsgNewsByTypeFromLocal: FetchPsgNewsByTypeFromLocal,
    private val savePsgNewsInLocal: SavePsgNewsInLocal,
    private val fetchPsgNewsFromLocal: FetchPsgNewsFromLocal
) : ViewModel() {
    private val disposables = CompositeDisposable()
    private val list = mutableListOf<NewsDetails>()
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
            .subscribe({ result ->
                if (result.isNullOrEmpty()) {
                    fetchListNew()
                } else {
                    _uiNewsEvent.value = UiNewsEvent.DisplayAllNews(result)
                    list.addAll(result)
                }
            }, {
                Timber.e(it.toString())
            })
            .also { disposables.add(it) }
    }

    private fun saveData(news: List<NewsDetails>) {
        savePsgNewsInLocal.invoke(news).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Timber.d("success inset data")
            }, { exception ->
                Timber.e(exception.toString())
            }).also {
                disposables.add(it)
            }
    }

    private fun fetchListNew() {
        fetchPsgNews.invoke()
            .observeOn(Schedulers.io())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result ->
                _uiNewsEvent.value = UiNewsEvent.DisplayNewsByType(result.news)
                saveData(result.news)
            }, { exception -> Timber.e(exception.toString())
            })
            .also { disposables.add(it) }
    }

    fun fetchByType(typeEnum: NewsDetailsTypeEnum) {
        fetchPsgNewsByTypeFromLocal.invoke(typeEnum)
            .observeOn(Schedulers.io())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result -> _uiNewsEvent.value = UiNewsEvent.DisplayNewsByType(result) },
                { exception ->
                    Timber.e(exception.toString())
                })
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
            UiNewsEvent.DisplayNewsSearchedByTitle(list.filter {
                it.title!!.toLowerCase(Locale.ROOT).contains(
                    text.toLowerCase(
                        Locale.ROOT
                    )
                )
            })
    }
}