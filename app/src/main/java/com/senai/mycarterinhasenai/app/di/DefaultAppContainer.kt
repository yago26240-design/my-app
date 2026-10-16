package com.senai.mycarterinhasenai.app.di

import com.senai.mycarterinhasenai.core.auth.SessionTokenStore
import com.senai.mycarterinhasenai.core.network.NetworkClient
import com.senai.mycarterinhasenai.feature.login.data.remote.service.AuthApi
import com.senai.mycarterinhasenai.feature.login.data.repository.ApiLoginRepositoryImpl
import com.senai.mycarterinhasenai.feature.login.domain.repository.LoginRepository
import com.senai.mycarterinhasenai.feature.unidadecurriculares.data.remote.service.UnidadeCurricularApi
import com.senai.mycarterinhasenai.feature.unidadecurriculares.data.repository.ApiUnidadeCurricularRepositoryImpl
import com.senai.mycarterinhasenai.feature.unidadecurriculares.domain.repository.UnidadeCurricularRepository
import com.senai.mycarterinhasenai.feature.professor.turmas.data.remote.service.TurmaProfessorApi
import com.senai.mycarterinhasenai.feature.professor.turmas.data.repository.ApiTurmaProfessorRepositoryImpl
import com.senai.mycarterinhasenai.feature.professor.turmas.domain.repository.TurmaProfessorRepository
import com.senai.mycarterinhasenai.feature.professor.unidadecurriculares.data.remote.service.UnidadeCurricularProfessorApi
import com.senai.mycarterinhasenai.feature.professor.unidadecurriculares.data.repository.ApiUnidadeCurricularProfessorRepositoryImpl
import com.senai.mycarterinhasenai.feature.professor.unidadecurriculares.domain.repository.UnidadeCurricularProfessorRepository


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
        ApiLoginRepositoryImpl(
            api = authApi
        )
    }
    override val unidadeCurricularRepository : UnidadeCurricularRepository by lazy {
        ApiUnidadeCurricularRepositoryImpl(
            api = unidadeCurricularApi
        )
    }
    private val turmaProfessorApi : TurmaProfessorApi by lazy {
        networkClient.createAuthenticated(TurmaProfessorApi::class.java)
    }

    private val ucProfessorApi : UnidadeCurricularProfessorApi by lazy {
        networkClient.createAuthenticated(UnidadeCurricularProfessorApi::class.java)
    }

    override val turmaProfessorRepository : TurmaProfessorRepository by lazy {
        ApiTurmaProfessorRepositoryImpl(api = turmaProfessorApi)
    }

    override val unidadeCurricularProfessorRepository : UnidadeCurricularProfessorRepository by lazy {
        ApiUnidadeCurricularProfessorRepositoryImpl(api = ucProfessorApi)
    }

    companion object {
        private const val BASE_URL = "http://10.0.2.2:8080/"
    }
}