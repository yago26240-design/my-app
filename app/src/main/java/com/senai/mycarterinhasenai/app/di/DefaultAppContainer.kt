package com.senai.mycarterinhasenai.app.di

import com.senai.mycarterinhasenai.core.auth.SessionTokenStore
import com.senai.mycarterinhasenai.core.network.NetworkClient
import com.senai.mycarterinhasenai.feature.login.data.remote.service.AuthApi
import com.senai.mycarterinhasenai.feature.login.data.repository.ApiLoginRepositoryImpl
import com.senai.mycarterinhasenai.feature.login.data.repository.FakeAuthRepository
import com.senai.mycarterinhasenai.feature.login.domain.repository.LoginRepository
import com.senai.mycarterinhasenai.feature.unidadecurriculares.data.remote.service.UnidadeCurricularApi
import com.senai.mycarterinhasenai.feature.unidadecurriculares.data.repository.ApiUnidadeCurricularRepositoryImpl
import com.senai.mycarterinhasenai.feature.unidadecurriculares.domain.repository.UnidadeCurricularRepository
import kotlin.getValue


class DefaultAppContainer : AppContainer {
    override val sessionTokenStore : SessionTokenStore = SessionTokenStore()
    private val networkClient =
        NetworkClient(
            baseUrl = BASE_URL,
            sessionTokenStore = SessionTokenStore()
        )

    private val authApi : AuthApi by lazy {
        networkClient.createPublic(
            AuthApi::class.java
        )
    }
    private val unidadeCurricularApi : UnidadeCurricularApi by lazy {
        networkClient.createAuthenticated(
            UnidadeCurricularApi::class.java
        )
    }
    override val loginRepository : LoginRepository by lazy {
        if (USE_FAKE_LOGIN_REPOSITORY ) {
            FakeAuthRepository()
        } else {
            ApiLoginRepositoryImpl(
                api =authApi
            )
        }
    }
    override val unidadeCurricularRepository : UnidadeCurricularRepository by lazy {
        ApiUnidadeCurricularRepositoryImpl(
            api = unidadeCurricularApi
        )
    }
    companion object {
        private const val BASE_URL = "http://10.0.2.2:8080/"
        private const val USE_FAKE_LOGIN_REPOSITORY = false
    }
}