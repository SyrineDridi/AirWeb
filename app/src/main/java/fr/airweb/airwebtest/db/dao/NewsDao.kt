package fr.airweb.airwebtest.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import fr.airweb.airwebtest.db.entities.NewsEntity
import io.reactivex.Completable
import io.reactivex.Observable

@Dao
interface NewsDao {
    @Query("SELECT * FROM news_entity where type=:type")
    fun getByType(type: String): Observable<List<NewsEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(news: List<NewsEntity?>) :Completable

    @Query("SELECT * FROM news_entity")
    fun getAll(): Observable<List<NewsEntity>>

}