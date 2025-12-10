package fr.eni.ecole.enishop.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import fr.eni.ecole.enishop.bo.Article

@Dao
interface ArticleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(article: Article): Long
    @Delete
    suspend fun delete(article: Article)

    @Query("SELECT * FROM Article WHERE id = :id")
    suspend fun findById(id: Long): Article?

    @Query("SELECT * FROM Article")
    suspend fun findAll(): List<Article>
}
