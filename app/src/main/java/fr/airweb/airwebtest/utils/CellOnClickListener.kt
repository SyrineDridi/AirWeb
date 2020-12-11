package fr.airweb.airwebtest.utils

import fr.airweb.airwebtest.domain.models.PsgModel

interface CellClickListener {
    fun onCellClickListener(psgModel: PsgModel)
}