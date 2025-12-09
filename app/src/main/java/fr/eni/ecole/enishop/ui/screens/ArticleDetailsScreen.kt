package fr.eni.ecole.enishop.ui.screens

import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import fr.eni.ecole.enishop.bo.Article
import fr.eni.ecole.enishop.utils.toFrenchFormat
import fr.eni.ecole.enishop.utils.toPriceFormat
import fr.eni.ecole.enishop.vm.ArticleDetailViewModel
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.ui.platform.testTag
import androidx.core.net.toUri

@Composable
fun ArticleDetailsScreen(
    articleId: Long?,
    viewModel: ArticleDetailViewModel = viewModel(factory = ArticleDetailViewModel.Factory),
    modifier: Modifier = Modifier
) {
    val article by viewModel.article.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(articleId) {
        viewModel.getArticle(articleId)
    }

    ArticleDetails(
        article = article,
        onArticleNameClick = { articleName ->
            val intent = Intent(Intent.ACTION_VIEW).apply {
                data = "https://www.google.com/search?q=${Uri.encode(articleName + " Eni-shop")}".toUri()
            }
            context.startActivity(intent)
        },
        modifier = modifier
    )

    if (article == null) {
        Text("Chargement...", Modifier.padding(16.dp))
    } else {
        ArticleDetails(article!!, modifier)
    }
}

@Composable
fun ArticleDetails(
    article: Article?,
    modifier: Modifier = Modifier,
    onArticleNameClick: (String) -> Unit,
) {
    if (article != null) {
        Column(
            verticalArrangement = Arrangement.SpaceEvenly,
            modifier = modifier.fillMaxSize().padding(16.dp)
    val context = LocalContext.current
    val googleUrl = "https://www.google.com/search?q="

    Column(
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = modifier.fillMaxSize().padding(16.dp)
    ) {
        Text(
            text = article.name,
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.clickable {
                val query = "${article.name} EniShop"
                val intent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("$googleUrl$query")
                )
                context.startActivity(intent)
            }
        )
        Surface(
            color = MaterialTheme.colorScheme.inverseOnSurface,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = article.name,
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .clickable { onArticleNameClick(article.name) }
                    .fillMaxWidth()
                    .testTag("artName")
            )
            Surface(
                color = MaterialTheme.colorScheme.inverseOnSurface,
                modifier = Modifier.fillMaxWidth()
            ) {
                AsyncImage(
                    model = article.urlImage,
                    contentDescription = article.name,
                    modifier = Modifier.height(250.dp)
                )
            }
            Text(
                text = article.description,
                textAlign = TextAlign.Justify
            )
            Spacer(modifier.padding(horizontal = 8.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = modifier.fillMaxWidth()
            ) {
                Text(text = "Prix : ${article.price.toPriceFormat()} €")
                Text(text = "Date de sortie : ${article.date.toFrenchFormat()}")
            }
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = modifier.fillMaxWidth()
            ) {
                Checkbox(
                    checked = true,
                    onCheckedChange = null
                )
                Text(text = "Favoris ?")
            }
        }
    }else {
        Text(
            text = "Il n'y a pas d'article correspondant à votre recherche"
        )
    }
}