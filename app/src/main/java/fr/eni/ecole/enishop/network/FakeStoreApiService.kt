package fr.eni.ecole.enishop.network

import fr.eni.ecole.enishop.bo.Article
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface FakeStoreApiService {
    @GET("products")
    suspend fun getArticles(): List<Article>

    @GET("products/categories")
    suspend fun getCategories(): List<String>

    @GET("products/{id}")
    suspend fun getArticleById(
        @Path("id") id: Long
    ): Article

    @POST("products")
    suspend fun createArticle(
        @Body article: Article?
    ): Article
}