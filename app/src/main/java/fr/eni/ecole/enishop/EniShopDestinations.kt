package fr.eni.ecole.enishop

import androidx.navigation.NavType
import androidx.navigation.navArgument

object DetailDestination {
    val route: String = "detail"
    val idValueArgArticle = "idArticle"

    val args = listOf(
        navArgument(idValueArgArticle) {
            type = NavType.LongType
        }
    )
    val routeWithArgs = "${DetailDestination.route}/{$idValueArgArticle}"
}

object ListDestination {
    val route: String = "liste"
}

object HomeDestination {
    val route: String = "home"
}

object AddDestination {
    val route : String = "addArticle"
}