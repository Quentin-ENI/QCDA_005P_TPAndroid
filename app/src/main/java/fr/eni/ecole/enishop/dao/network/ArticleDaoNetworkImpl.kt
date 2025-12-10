package fr.eni.ecole.enishop.dao.network

import fr.eni.ecole.enishop.bo.Article
import fr.eni.ecole.enishop.dao.ArticleDao

class ArticleDaoNetworkImpl : ArticleDao {

    override suspend fun insert(article: Article): Long {
        TODO("Not yet implemented")
    }

    override suspend fun delete(article: Article) {
        TODO("Not yet implemented")
    }

    override suspend fun findById(id: Long): Article? {
        TODO("Not yet implemented")
    }

    override suspend fun findAll(): List<Article> {
        TODO("Not yet implemented")
    }
}
