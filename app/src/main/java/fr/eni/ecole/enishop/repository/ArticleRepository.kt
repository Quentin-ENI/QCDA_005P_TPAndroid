package fr.eni.ecole.enishop.repository

import fr.eni.ecole.enishop.bo.Article
import fr.eni.ecole.enishop.dao.ArticleDao
import fr.eni.ecole.enishop.exceptions.ArticleServiceException
import fr.eni.ecole.enishop.network.FakeStoreApiService

class ArticleRepository(
    private val articleDaoRoom: ArticleDao,
    private val fakeStoreService: FakeStoreApiService
) {
    suspend fun getArticle(id: Long): Article {
        return fakeStoreService.getArticleById(id)
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
        return fakeStoreService.getArticles()
    }

    suspend fun getAllCategories(): List<String> {
        return fakeStoreService.getCategories()
    }

    suspend fun createArticle(article: Article): Article {
        val article = fakeStoreService.createArticle(article)
        if (article == null) {
            throw ArticleServiceException(message="L'article n'a pas pu être créé")
        }
        return article
    }
}
