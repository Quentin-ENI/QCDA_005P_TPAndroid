package fr.eni.ecole.enishop.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import kotlin.getValue

object RetrofitClient {
    private val BASE_URL = "https://fakestoreapi.com/"

    val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    val fakeStoreApiService: FakeStoreApiService by lazy {
        retrofit.create(FakeStoreApiService::class.java)
    }
}