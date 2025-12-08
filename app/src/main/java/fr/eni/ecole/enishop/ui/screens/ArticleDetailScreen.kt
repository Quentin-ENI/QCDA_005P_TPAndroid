package fr.eni.ecole.enishop.ui.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import fr.eni.ecole.enishop.vm.ArticleDetailViewModel

@Composable
fun ArticleDetailScreen(
    articleId: Long,
    viewModel: ArticleDetailViewModel = viewModel()
) {
    val context = LocalContext.current

    val article by viewModel.article.collectAsState()

    // Charge l’article au démarrage
    LaunchedEffect(articleId) {
        viewModel.loadArticle(articleId)
    }

    if (article == null) {
        Text("Chargement...", Modifier.padding(16.dp))
    } else {

        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            // Image
            AsyncImage(
                model = article!!.urlImage,
                contentDescription = article!!.name,
                modifier = Modifier.fillMaxWidth()
            )

            // Titre cliquable → ouvre Google
            Text(
                text = article!!.name,
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.clickable {
                    val query = "${article!!.name} EniShop"
                    val intent = Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://www.google.com/search?q=$query")
                    )
                    context.startActivity(intent)
                }
            )

            Text(text = "Prix : ${article!!.price} €", style = MaterialTheme.typography.bodyLarge)

            Text(text = "Catégorie : ${article!!.category}")

            Text(text = article!!.description)
        }
    }
}
