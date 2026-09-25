package com.senai.mycarterinhasenai.app.di

import com.senai.mycarterinhasenai.core.auth.AuthTokenStore
import com.senai.mycarterinhasenai.feature.login.data.repository.LoginRepository
import com.senai.mycarterinhasenai.feature.unidadecurriculares.domain.repository.UnidadeCurricularRepository


interface AppContainer {

    val loginRepository: LoginRepository

    val unidadeCurricularRepository: UnidadeCurricularRepository

    val authTokenStore: AuthTokenStore
}