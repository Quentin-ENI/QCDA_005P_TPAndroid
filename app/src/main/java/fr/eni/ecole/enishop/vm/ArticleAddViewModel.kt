package fr.eni.ecole.enishop.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import fr.eni.ecole.enishop.bo.Article
import fr.eni.ecole.enishop.db.AppDatabase
import fr.eni.ecole.enishop.network.RetrofitClient
import fr.eni.ecole.enishop.repository.ArticleRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ArticleAddViewModel(
    private val articleRepository: ArticleRepository
) : ViewModel() {

    private val _article = MutableStateFlow<Article>(Article())
    val article = _article.asStateFlow()

    private val _categories = MutableStateFlow<List<String>>(emptyList())
    val categories = _categories.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _categories.value = articleRepository.getAllCategories()
        }
    }

    fun createArticle() {
        viewModelScope.launch(Dispatchers.IO) {
            articleRepository.createArticle(_article.value)
        }
    }

    fun setArticleName(name: String) {
        _article.value = _article.value.copy(name = name)
    }

    fun setArticlePrice(price: String) {
        _article.value = _article.value.copy(price = price.toDouble())
    }

    fun setArticleDescription(description: String) {
        _article.value = _article.value.copy(description = description)
    }

    fun setArticleCategory(category: String) {
        _article.value = _article.value.copy(category = category)
    }

    companion object {
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(
                modelClass: Class<T>,
                extras: CreationExtras
            ): T {
                val application = checkNotNull(extras[APPLICATION_KEY])
                return ArticleAddViewModel(
                    ArticleRepository(
                        AppDatabase.getInstance(application.applicationContext).articleDao(),
                        RetrofitClient.fakeStoreApiService
                    )
                ) as T
            }
        }
    }
}