package fr.eni.ecole.enishop.dao

import fr.eni.ecole.enishop.dao.memory.ArticleDaoMemoryImpl
import fr.eni.ecole.enishop.dao.network.ArticleDaoNetworkImpl

abstract class DaoFactory {
    companion object {
        fun createArticleDAO(type: DaoType): ArticleDao {
            return when (type) {
                DaoType.MEMORY -> ArticleDaoMemoryImpl()
                else -> ArticleDaoNetworkImpl()
            }
        }
    }
}