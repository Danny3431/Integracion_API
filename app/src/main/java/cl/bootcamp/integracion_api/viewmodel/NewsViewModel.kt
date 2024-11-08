package cl.bootcamp.integracion_api.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.bootcamp.integracion_api.model.Article
import cl.bootcamp.integracion_api.repository.NewsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class NewsViewModel(
    private val repository: NewsRepository = NewsRepository()
) : ViewModel() {

    private val _topHeadlines = MutableStateFlow<List<Article>>(emptyList())
    val topHeadlines: StateFlow<List<Article>> = _topHeadlines.asStateFlow()

    private val _searchResults = MutableStateFlow<List<Article>>(emptyList())
    val searchResults: StateFlow<List<Article>> = _searchResults.asStateFlow()

    private val _selectedArticle = MutableStateFlow<Article?>(null)
    val selectedArticle: StateFlow<Article?> = _selectedArticle.asStateFlow()

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    init {
        fetchTopHeadlines() // Cargar titulares al iniciar
    }

    // Método para obtener titulares principales
    fun fetchTopHeadlines(country: String = "us") {
        _loading.value = true
        viewModelScope.launch {
            try {
                val response = repository.getTopHeadlines(country)
                if (response.isNotEmpty()) {
                    _topHeadlines.value = response
                    _error.value = null
                } else {
                    _error.value = "No hay titulares disponibles"
                }
            } catch (e: Exception) {
                _error.value = e.localizedMessage ?: "Error al cargar titulares"
            } finally {
                _loading.value = false
            }
        }
    }

    // Método para buscar noticias por query
    fun searchNews(query: String) {
        _loading.value = true
        viewModelScope.launch {
            try {
                val response = repository.searchNews(query)
                if (response.isNotEmpty()) {
                    _searchResults.value = response
                    _error.value = null
                } else {
                    _error.value = "No hay resultados para \"$query\""
                }
            } catch (e: Exception) {
                _error.value = e.localizedMessage ?: "Error al buscar noticias"
            } finally {
                _loading.value = false
            }
        }
    }

    // Recupera un artículo por ID o título de la lista de titulares
    fun getArticleByIdOrName(articleId: String?, articleName: String?): Article? {
        return topHeadlines.value.find {
            (articleId != null && it.id == articleId) ||
                    (articleName != null && it.title == articleName)
        }
    }
    // Seleccionar un artículo específico
    fun setSelectedArticle(article: Article) {
        _selectedArticle.value = article
    }

    // Limpiar el artículo seleccionado
    fun cleanSelectedArticle() {
        _selectedArticle.value = null
    }
}