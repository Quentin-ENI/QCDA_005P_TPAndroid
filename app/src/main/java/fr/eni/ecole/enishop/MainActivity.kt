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
import fr.eni.ecole.enishop.ui.screens.ArticleDetailsScreen
import fr.eni.ecole.enishop.ui.shared.TopBar
import fr.eni.ecole.enishop.ui.theme.ENIShopTheme

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
        ArticleDetailsScreen(1)
    }
}