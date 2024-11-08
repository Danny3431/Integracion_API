package cl.bootcamp.integracion_api.data

import cl.bootcamp.integracion_api.model.NewsResponse
import cl.bootcamp.integracion_api.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    @GET("top-headlines")
    suspend fun getTopHeadlines(
        @Query("country") country: String =Constants.COUNTRY,
        @Query("apiKey") apiKey: String = Constants.API_KEY
    ): Response<NewsResponse>

    @GET("everything")
    suspend fun getNewsByQuery(
        @Query("q") query: String,
        @Query("apiKey") apiKey: String = Constants.API_KEY
    ): Response<NewsResponse>
}