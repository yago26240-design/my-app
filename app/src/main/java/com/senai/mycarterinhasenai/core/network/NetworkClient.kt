package com.senai.mycarterinhasenai.core.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.senai.mycarterinhasenai.core.auth.SessionTokenStore
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

class NetworkClient(
    private val baseUrl: String,
    private val sessionTokenStore: SessionTokenStore
) {
    private val json = Json { ignoreUnknownKeys = true }

    private fun logging(authenticated: Boolean) = HttpLoggingInterceptor().apply {
        redactHeader("Authorization")
        // A requisição de login pode conter senha: jamais registrar seu corpo.
        level = if (authenticated) HttpLoggingInterceptor.Level.BODY
                else HttpLoggingInterceptor.Level.BASIC
    }

    private val publicClient by lazy {
        OkHttpClient.Builder().addInterceptor(logging(false)).build()
    }

    private val authenticatedClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor(sessionTokenStore))
            .addInterceptor(logging(true))
            .build()
    }

    private fun retrofit(client: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(client)
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .build()

    fun <T : Any> createPublic(serviceClass: Class<T>): T =
        retrofit(publicClient).create(serviceClass)

    fun <T : Any> createAuthenticated(serviceClass: Class<T>): T =
        retrofit(authenticatedClient).create(serviceClass)
}
