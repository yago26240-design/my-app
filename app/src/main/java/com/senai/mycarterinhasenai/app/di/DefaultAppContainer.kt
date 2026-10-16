package com.senai.mycarterinhasenai.app.di

import com.senai.mycarterinhasenai.core.auth.SessionTokenStore
import com.senai.mycarterinhasenai.core.network.NetworkClient
import com.senai.mycarterinhasenai.feature.login.data.remote.service.AuthApi
import com.senai.mycarterinhasenai.feature.login.data.repository.ApiLoginRepositoryImpl
import com.senai.mycarterinhasenai.feature.login.domain.repository.LoginRepository
import com.senai.mycarterinhasenai.feature.unidadecurriculares.data.remote.service.UnidadeCurricularApi
import com.senai.mycarterinhasenai.feature.unidadecurriculares.data.repository.ApiUnidadeCurricularRepositoryImpl
import com.senai.mycarterinhasenai.feature.unidadecurriculares.domain.repository.UnidadeCurricularRepository
import com.senai.mycarterinhasenai.feature.professor.data.remote.service.ProfessorApi
import com.senai.mycarterinhasenai.feature.professor.data.repository.ApiProfessorRepositoryImpl
import com.senai.mycarterinhasenai.feature.professor.domain.repository.ProfessorRepository

class DefaultAppContainer:AppContainer {
 override val sessionTokenStore=SessionTokenStore()
 private val networkClient=NetworkClient("http://10.0.2.2:8080/",sessionTokenStore)
 private val authApi by lazy { networkClient.createPublic(AuthApi::class.java) }
 private val alunoApi by lazy { networkClient.createAuthenticated(UnidadeCurricularApi::class.java) }
 private val professorApi by lazy { networkClient.createAuthenticated(ProfessorApi::class.java) }
 override val loginRepository:LoginRepository by lazy { ApiLoginRepositoryImpl(authApi) }
 override val unidadeCurricularRepository:UnidadeCurricularRepository by lazy { ApiUnidadeCurricularRepositoryImpl(alunoApi) }
 override val professorRepository:ProfessorRepository by lazy { ApiProfessorRepositoryImpl(professorApi) }
}
