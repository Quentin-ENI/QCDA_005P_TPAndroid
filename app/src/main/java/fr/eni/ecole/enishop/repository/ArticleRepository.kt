package fr.eni.ecole.enishop.repository

import fr.eni.ecole.enishop.bo.Article
import fr.eni.ecole.enishop.dao.DaoFactory
import fr.eni.ecole.enishop.dao.DaoType

class ArticleRepository {
    val articleDao = DaoFactory.createArticleDAO(DaoType.MEMORY);

    fun getArticle(id: Long): Article? {
        return articleDao.findById(id);
    }

    fun addArticle(article: Article): Long {
        return articleDao.insert(article);
    }
}