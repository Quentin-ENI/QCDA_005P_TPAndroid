package fr.eni.ecole.enishop.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.eni.ecole.enishop.bo.Article
import fr.eni.ecole.enishop.repository.ArticleRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ArticleDetailViewModel(
    private val repository: ArticleRepository = ArticleRepository()
) : ViewModel() {

    private val _article = MutableStateFlow<Article?>(null)
    val article: StateFlow<Article?> = _article

    fun loadArticle(id: Long) {
        viewModelScope.launch {
            val result = repository.getArticle(id)
            _article.value = result
        }
    }
}
