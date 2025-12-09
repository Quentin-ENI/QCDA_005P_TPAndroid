package fr.eni.ecole.enishop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import fr.eni.ecole.enishop.ui.screens.ArticleAddScreen
import fr.eni.ecole.enishop.ui.screens.ArticleDetailsScreen
import fr.eni.ecole.enishop.ui.screens.ArticleListScreen
import fr.eni.ecole.enishop.ui.theme.ENIShopTheme
import fr.eni.ecole.enishop.ui.shared.TopBar

private const val TAG = "MAIN SCREEN"

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ENIShopTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopBar()
                    }
                ) { innerPadding ->
                    ENIShopApp(
                        modifier = Modifier.padding(innerPadding),
                        navController = rememberNavController()
                    )
                }
            }
        }
    }
}

@Composable
fun ENIShopApp(
    modifier: Modifier = Modifier,
    navController : NavHostController
) {
    Column(
        modifier = modifier
    ) {
        EniShopNAvHost(navController)
    }
}

@Composable
fun EniShopNAvHost(
    navController : NavHostController,
    startDestination : String = ListDestination.route,
    modifier: Modifier = Modifier,
){
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ){
        composable(HomeDestination.route){
            ENIShopApp(navController = navController)
        }
        composable(ListDestination.route){
            ArticleListScreen(
                onArticleCardClick = {
                    navController.navigate("${DetailDestination.route}/$it")
                },
                onAddArticleClick = {
                    navController.navigate(AddDestination.route)
                }
            )
        }
        composable(
            route = DetailDestination.routeWithArgs,
            arguments = DetailDestination.args
        ) {
            navBackStackEntry ->
                val idArticleValue = navBackStackEntry
                    .arguments?.getLong(DetailDestination.idValueArgArticle)
            ArticleDetailsScreen(idArticleValue)
        }
        composable(route = AddDestination.route){
            ArticleAddScreen(navController)
        }
    }
}