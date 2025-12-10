package fr.eni.ecole.enishop.vm

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import fr.eni.ecole.enishop.bo.Article
import fr.eni.ecole.enishop.repository.ArticleRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ArticleDetailViewModel(private val repository: ArticleRepository) : ViewModel() {

    private val _article = MutableStateFlow<Article?>(null)
    val article = _article.asStateFlow()
    private val _isFavorite = MutableStateFlow(false)
    val isFavorite = _isFavorite.asStateFlow()

    fun loadArticle(id: Long) {
        viewModelScope.launch {
            val fetchedArticle = repository.getArticle(id)
            _article.value = fetchedArticle

            if (fetchedArticle != null) {
                _isFavorite.value = repository.isFavorite(id)
            }
        }
    }

    fun toggleFavorite() {
        viewModelScope.launch {
            val currentArticle = _article.value ?: return@launch
            val currentFavStatus = _isFavorite.value

            if (currentFavStatus) {
                repository.removeFavorite(currentArticle)
            } else {
                repository.addFavorite(currentArticle)
            }
            _isFavorite.value = !currentFavStatus
        }
    }

    companion object {
        fun provideFactory(context: Context): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
                val repository = ArticleRepository(context)
                return ArticleDetailViewModel(repository) as T
            }
        }
    }
}

