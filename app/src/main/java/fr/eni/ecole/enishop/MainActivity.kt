package fr.eni.ecole.enishop

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import fr.eni.ecole.enishop.dao.memory.ArticleDaoMemoryImpl
import fr.eni.ecole.enishop.repository.ArticleRepository
import fr.eni.ecole.enishop.ui.theme.ENIShopTheme
import java.util.Date

private const val TAG = "MAIN SCREEN"

class MainActivity : ComponentActivity() {


    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val articles =

            ENIShopTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(
                            title = { TopBar(Modifier) }
                        )
                    }
                ) { innerPadding ->
                    ArticleDetails(
                        name = "Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops",
                        description = "Your perfect pack for everyday use and walks in the forest. Stash your laptop (up to 15 inches) in the padded sleeve, your everyday",
                        price = 109.95,
                        urlImage = "https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_t.png",
                        date = Date(),
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
            var articleRepository = ArticleRepository()
            var article = articleRepository.getArticle(1);
            Log.i(TAG, "L'article $article a été récupéré")
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ENIShopTheme {
        Greeting("Android")
    }
}