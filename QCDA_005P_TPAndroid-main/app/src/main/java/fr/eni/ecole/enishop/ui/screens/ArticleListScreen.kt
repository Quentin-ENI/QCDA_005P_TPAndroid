package fr.eni.ecole.enishop.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import fr.eni.ecole.enishop.bo.Article
import fr.eni.ecole.enishop.vm.ArticleListViewModel

@Composable
fun ArticleListScreen(
    modifier: Modifier = Modifier,
    viewModel: ArticleListViewModel = viewModel(factory = ArticleListViewModel.Factory),
    onArticleCardClick: (String) -> Unit,
) {
    val articles by viewModel.currentArticles.collectAsState()
    val categories = viewModel.categories

    var selectedCategory by rememberSaveable { mutableStateOf<String?>(null) }

    Box(
        modifier = modifier.fillMaxSize()
    ) {
        Column(
            modifier = modifier
                .padding(16.dp)
                .fillMaxSize()
        ) {
            CategoryFilterChip(
                categories = categories,
                selectedCategory = selectedCategory,
                onCategorySelected = { cat ->
                    if (selectedCategory == cat) {
                        selectedCategory = null
                    } else {
                        selectedCategory = cat
                    }
                    viewModel.filterByCategory(selectedCategory)
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            ArticleList(
                articles = articles,
                onArticleCardClick = onArticleCardClick
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryFilterChip(
    categories: List<String>,
    selectedCategory: String?,
    onCategorySelected: (String) -> Unit
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(categories) { category ->
            val isSelected = selectedCategory == category
            FilterChip(
                selected = isSelected,
                onClick = { onCategorySelected(category) },
                label = { Text(category) },
                leadingIcon = if (isSelected) {
                    { Icon(imageVector = Icons.Filled.Check, contentDescription = "Selected") }
                } else null
            )
        }
    }
}

@Composable
fun ArticleList(
    articles: List<Article>,
    onArticleCardClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    if (articles.isEmpty()) {
        Row(
            modifier = modifier.fillMaxWidth()
        ) {
            Text(
                text="Il n'y a pas d'articles correspondants à la sélection",
                textAlign = TextAlign.Center
            )
        }
    } else {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(bottom = 16.dp)
        ) {
            items(articles) { article ->
                ArticleItem(article = article,
                    onArticleCardClick = onArticleCardClick)
            }
        }
    }
}

@Composable
fun ArticleItem(
    article: Article,
    onArticleCardClick: (String) -> Unit,
    ) {
    Card(
        modifier = Modifier
            .clickable { onArticleCardClick(article.id.toString()) }
            .fillMaxWidth()
            .height(200.dp),
        border = BorderStroke(
            width=2.dp,
            color=MaterialTheme.colorScheme.primary
        ),
        colors = CardColors(
            containerColor = Color.White,
            contentColor = MaterialTheme.colorScheme.secondary,
            disabledContentColor = Color.Gray,
            disabledContainerColor = Color.Gray
        )
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(8.dp)
        ) {
            AsyncImage(
                model = article.urlImage,
                contentDescription = article.name,
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .border(
                        width=2.dp,
                        color=MaterialTheme.colorScheme.tertiary,
                        CircleShape
                    )
                    .padding(8.dp)

            )
            Text(
                text = article.name,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )
            Text(
                text = "${article.price} €",
                fontSize = 16.sp,
            )
        }
    }
}

@Composable
fun ArticleListFAB(
    onNavigateToArticleForm: () -> Unit,
    modifier: Modifier = Modifier
) {
    FloatingActionButton(
        onClick = onNavigateToArticleForm,
        modifier = Modifier,
        shape = CircleShape
    ) {
        Icon(Icons.Filled.Add, "Go to article form.")
    }
}
