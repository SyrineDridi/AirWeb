package fr.airweb.airwebtest.utils

import fr.airweb.airwebtest.domain.models.NewsDetails

interface CellClickListener {
    fun onCellClickListener(psgModel: NewsDetails)
}