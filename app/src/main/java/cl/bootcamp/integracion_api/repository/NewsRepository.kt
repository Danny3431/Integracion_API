package cl.bootcamp.integracion_api.repository


import cl.bootcamp.integracion_api.data.RetrofitInstance
import cl.bootcamp.integracion_api.model.Article
import cl.bootcamp.integracion_api.model.NewsResponse
import retrofit2.Response


class NewsRepository {
    // Método para obtener titulares principales
    suspend fun getTopHeadlines(country: String): List<Article> {
        return try {
            val response = RetrofitInstance.api.getTopHeadlines(country)
            if (response.isSuccessful && response.body() != null) {
                response.body()!!.articles.map { article ->
                    article.copy(id = article.id ?: article.title.hashCode().toString())
                }
            } else {
                emptyList()
            }
        } catch (e: Exception) {
            emptyList()
        }
    }
    // Método para buscar noticias por query
    suspend fun searchNews(query: String): List<Article> {
        return try {
            val response = RetrofitInstance.api.getNewsByQuery(query)
            if (response.isSuccessful && response.body() != null) {
                response.body()!!.articles.map { article ->
                    article.copy(id = article.id ?: article.title.hashCode().toString())
                }
            } else {
                emptyList()
            }
        } catch (e: Exception) {
            emptyList()
        }
    }
}