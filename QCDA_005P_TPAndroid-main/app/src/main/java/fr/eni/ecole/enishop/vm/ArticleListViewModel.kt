package fr.eni.ecole.enishop.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import fr.eni.ecole.enishop.bo.Article
import fr.eni.ecole.enishop.repository.ArticleRepository

class ArticleListViewModel(private val repository: ArticleRepository) : ViewModel() {

    private var _allArticles = emptyList<Article>()

    private val _currentArticles = MutableStateFlow<List<Article>>(emptyList())
    val currentArticles = _currentArticles.asStateFlow()

    val categories = listOf("electronics", "jewelery", "men's clothing", "women's clothing")

    init {
        _allArticles = repository.getAllArticles()
        _currentArticles.value = _allArticles
    }

    fun filterByCategory(category: String?) {
        if (category != null) {
            _currentArticles.value = _allArticles.filter { it.category == category }
        } else {
            _currentArticles.value = _allArticles
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
                val repository = ArticleRepository()
                return ArticleListViewModel(repository) as T
            }
        }
    }
}
