package com.senai.mycarterinhasenai.app.di

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.senai.mycarterinhasenai.app.navigation.Routes
import com.senai.mycarterinhasenai.app.session.SessionViewModel
import com.senai.mycarterinhasenai.app.session.SessionViewModelFactory
import com.senai.mycarterinhasenai.app.ui.AppShell
import com.senai.mycarterinhasenai.core.auth.SessionTokenStore
import com.senai.mycarterinhasenai.feature.carterinha.presentantion.component.CarteirinhaScreen
import com.senai.mycarterinhasenai.feature.carterinha.presentantion.screen.CarteirinhaScreen
import com.senai.mycarterinhasenai.feature.home.presentation.screen.HomeScreen
import com.senai.mycarterinhasenai.feature.login.data.remote.service.AuthApi
import com.senai.mycarterinhasenai.feature.login.data.repository.ApiLoginRepositoryImpl
import com.senai.mycarterinhasenai.feature.login.domain.repository.LoginRepository
import com.senai.mycarterinhasenai.feature.login.presention.LoginViewModel
import com.senai.mycarterinhasenai.feature.login.presention.factory.LoginViewModelFactory
import com.senai.mycarterinhasenai.feature.login.presention.screen.LoginScreen
import com.senai.mycarterinhasenai.feature.unidadecurriculares.data.remote.service.UnidadeCurricularApi
import com.senai.mycarterinhasenai.feature.unidadecurriculares.data.repository.ApiUnidadeCurricularRepositoryImpl
import com.senai.mycarterinhasenai.feature.unidadecurriculares.domain.repository.UnidadeCurricularRepository
import com.senai.mycarterinhasenai.feature.unidadecurriculares.presentation.UnidadeCurricularViewModel
import com.senai.mycarterinhasenai.feature.unidadecurriculares.presentation.factory.UnidadeCurricularViewModelFactory
import com.senai.mycarterinhasenai.feature.unidadecurriculares.presentation.screen.UnidadeCurricularScreen
import kotlin.getValue

class DefaultAppContainer : AppContainer {
    override val sessionTokenStore : SessionTokenStore = SessionTokenStore()
    private val networkClient =
        NetworkClient(
            baseUrl = BASE_URL,
            sessionTokenStore = sessionTokenStore
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
                api = authApi
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