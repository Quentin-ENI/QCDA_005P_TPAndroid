package fr.eni.ecole.enishop.network

import fr.eni.ecole.enishop.bo.Article
import retrofit2.http.GET

interface FakeStoreApiService {
    @GET("products")
    suspend fun getArticles(): List<Article>

    @GET("products/categories")
    suspend fun getCategories(): List<String>
}