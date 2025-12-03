package fr.eni.ecole.enishop

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import fr.eni.ecole.enishop.bo.Article
import fr.eni.ecole.enishop.repository.ArticleRepository
import fr.eni.ecole.enishop.ui.screens.ArticleDetailsScreen
import fr.eni.ecole.enishop.ui.theme.ENIShopTheme
import fr.eni.ecole.enishop.ui.shared.TopBar
import java.util.Date

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
                    ENIShopApp(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun ENIShopApp(modifier: Modifier = Modifier) {


    Column(
        modifier = modifier
    ) {
        ArticleDetailsScreen(
            articleId = 1
        )
    }
}