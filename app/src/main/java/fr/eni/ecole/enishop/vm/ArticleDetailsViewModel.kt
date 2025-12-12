package fr.eni.ecole.enishop.vm

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import fr.eni.ecole.enishop.bo.Article
import fr.eni.ecole.enishop.dao.memory.ArticleDaoMemoryImpl
import fr.eni.ecole.enishop.db.AppDatabase
import fr.eni.ecole.enishop.network.RetrofitClient
import fr.eni.ecole.enishop.repository.ArticleRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ArticleDetailsViewModel(private val repository: ArticleRepository) : ViewModel() {

    private val _article = MutableStateFlow<Article?>(null)
    val article = _article.asStateFlow()

    private val _isFavorite = MutableStateFlow<Boolean>(false)
    val isFavorite = _isFavorite.asStateFlow()

    fun loadArticle(articleId: Long) {
        viewModelScope.launch {
//            val fetchedArticle = repository.getArticle(articleId)
//            _article.value = fetchedArticle

//            if (fetchedArticle != null) {
//                _isFavorite.value = repository.isFavorite(articleId)
//            }
        }
    }

    fun toggleFavorite() {
        viewModelScope.launch {
            val article = _article.value
            val isFavorite = _isFavorite.value

            if (isFavorite) {
                repository.removeFavorite(article!!)
            } else {
                repository.addFavorite(article!!)
            }
            _isFavorite.value = !isFavorite
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(
                modelClass: Class<T>,
                extras: CreationExtras
            ): T {
                val application = checkNotNull(extras[APPLICATION_KEY])
                return ArticleDetailsViewModel(
                    ArticleRepository(
                        AppDatabase.getInstance(application.applicationContext).articleDao(),
                        RetrofitClient.fakeStoreApiService
                    )
                ) as T
            }
        }
    }
}

