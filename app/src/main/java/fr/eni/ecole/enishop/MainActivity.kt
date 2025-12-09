package fr.eni.ecole.enishop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import fr.eni.ecole.enishop.datastore.DataStoreManager
import fr.eni.ecole.enishop.nav.ArticleAddDestination
import fr.eni.ecole.enishop.nav.ArticleDetailsDestination
import fr.eni.ecole.enishop.nav.ArticleListDestination
import fr.eni.ecole.enishop.ui.screens.ArticleAddScreen
import fr.eni.ecole.enishop.ui.screens.ArticleDetailsScreen
import fr.eni.ecole.enishop.ui.screens.ArticleListFAB
import fr.eni.ecole.enishop.ui.screens.ArticleListScreen
import fr.eni.ecole.enishop.ui.shared.EniShopTopBar
import fr.eni.ecole.enishop.ui.theme.ENIShopTheme

private const val TAG = "MAIN SCREEN"

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val context = LocalContext.current

            val isDarkTheme by DataStoreManager
                .isDarkThemeEnabled(context)
                .collectAsState(initial = false)

            ENIShopTheme(darkTheme = isDarkTheme) {
                ENIShopApp()
            }
        }
    }
}

@Composable
fun ENIShopApp(
    navController : NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            EniShopTopBar(
                canBack = currentRoute != ArticleListDestination.route,
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        },
        floatingActionButton = {
            if (currentRoute == ArticleListDestination.route) {
                ArticleListFAB(
                    onNavigateToArticleForm = {
                        navController.navigate(ArticleAddDestination.route)
                    }
                )
            }
        }
    ) { innerPadding ->
        EniShopNavHost(
            navController = navController,
            modifier = Modifier.padding(innerPadding),
        )
    }
}

@Composable
fun EniShopNavHost(
    navController : NavHostController,
    startDestination : String = ArticleListDestination.route,
    modifier : Modifier = Modifier,
){
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ){
        composable(ArticleListDestination.route){
            ArticleListScreen(
                onArticleCardClick = {
                    navController.navigate("${ArticleDetailsDestination.route}/$it")
                },
            )
        }
        composable(
            route = ArticleDetailsDestination.routeWithArgs,
            arguments = ArticleDetailsDestination.args
        ) {
            navBackStackEntry ->
                val idArticleValue = navBackStackEntry
                    .arguments?.getLong(ArticleDetailsDestination.idArticleValueArg)
                ArticleDetailsScreen(idArticleValue)
        }
        composable(route = ArticleAddDestination.route){
            ArticleAddScreen(navController)
        }
    }
}