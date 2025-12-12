package fr.eni.ecole.enishop.repository

import fr.eni.ecole.enishop.bo.Article
import fr.eni.ecole.enishop.dao.ArticleDao
import fr.eni.ecole.enishop.network.FakeStoreApiService

class ArticleRepository(
    private val articleDaoRoom: ArticleDao,
    private val articleService: FakeStoreApiService
) {
//    suspend fun getArticle(id: Long): Article? {
//        return articleService.findById(id)
//    }
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
        return articleService.getArticles()
    }

    suspend fun getAllCategories(): List<String> {
        return articleService.getCategories()
    }
}
