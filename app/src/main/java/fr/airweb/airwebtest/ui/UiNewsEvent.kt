package fr.airweb.airwebtest.ui

import fr.airweb.airwebtest.domain.models.PsgModel

sealed class UiNewsEvent {
    data class DisplayAllNews(val news: List<PsgModel>) : UiNewsEvent()
    data class DisplayNewsByType(val newsByType: List<PsgModel>) : UiNewsEvent()
    data class DisplayNewsSortedByDate(val newsSortedByDate: List<PsgModel>) : UiNewsEvent()
    data class DisplayNewsSortedByTitle(val newsSortedByTitle: List<PsgModel>) : UiNewsEvent()
    data class DisplayNewsSearchedByTitle(val newsSearchedByTitle: List<PsgModel>) : UiNewsEvent()
}