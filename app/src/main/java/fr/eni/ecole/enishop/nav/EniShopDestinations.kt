package fr.eni.ecole.enishop.nav

import androidx.navigation.NavType
import androidx.navigation.navArgument

object ArticleDetailsDestination {
    val route: String = "detailsArticle"
    val idArticleValueArg = "idArticle"

    val args = listOf(
        navArgument(idArticleValueArg) {
            type = NavType.LongType
        }
    )
    val routeWithArgs = "${route}/{$idArticleValueArg}"
}

object ArticleListDestination {
    val route: String = "listArticles"
}

object ArticleAddDestination {
    val route : String = "addArticle"
}