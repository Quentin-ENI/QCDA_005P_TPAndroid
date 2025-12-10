package fr.eni.ecole.enishop.repository

import android.content.Context
import fr.eni.ecole.enishop.bo.Article
import fr.eni.ecole.enishop.dao.memory.ArticleDaoMemoryImpl
import fr.eni.ecole.enishop.db.AppDatabase

class ArticleRepository(context: Context) {
    private val articleDaoMemory = ArticleDaoMemoryImpl()
    private val articleDaoRoom = AppDatabase.getInstance(context).articleDao()
    suspend fun getArticle(id: Long): Article? {
        return articleDaoMemory.findById(id)
    }
    suspend fun addFavorite(article: Article) {
        articleDaoRoom.insert(article)
    }
    suspend fun removeFavorite(article: Article) {
        articleDaoRoom.delete(article)
    }
    suspend fun isFavorite(id: Long): Boolean {
        return articleDaoRoom.findById(id) != null
    }
    suspend fun getAllArticles(): List<Article> {
        return articleDaoMemory.findAll()
    }
}
