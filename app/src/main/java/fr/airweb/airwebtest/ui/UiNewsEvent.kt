package fr.airweb.airwebtest.ui

import fr.airweb.airwebtest.domain.models.NewsDetails

sealed class UiNewsEvent {
    data class DisplayAllNews(val news: List<NewsDetails>) : UiNewsEvent()
    data class DisplayNewsByType(val newsByType: List<NewsDetails>) : UiNewsEvent()
    data class DisplayNewsSortedByDate(val newsSortedByDate: List<NewsDetails>) : UiNewsEvent()
    data class DisplayNewsSortedByTitle(val newsSortedByTitle: List<NewsDetails>) : UiNewsEvent()
    data class DisplayNewsSearchedByTitle(val newsSearchedByTitle: List<NewsDetails>) : UiNewsEvent()
}