package fr.airweb.airwebtest.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "news_entity")
data class NewsEntity(
    @PrimaryKey
    val nid: Int,
    val type: String? = null,
    val date: String? = null,
    val title: String? = null,
    val picture: String? = null,
    val content: String? = null,
    val dateFormated: String? = null
)
