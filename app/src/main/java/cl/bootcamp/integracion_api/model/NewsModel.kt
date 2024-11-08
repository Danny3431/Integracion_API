package cl.bootcamp.integracion_api.model


data class NewsResponse(
    val status: String,
    val totalResults: Int,
    val articles: List<Article>
)

data class Article(
    val id: String? = null,
    val name: String? = null,
    val author: String? = null,
    val title: String,
    val description: String? = null,
    val url: String,
    val urlToImage: String? = null
) {
    // Generamos un ID único si `id` es null, basado en el título o la URL
    val uniqueId: String
        get() = id ?: title.hashCode().toString()
}