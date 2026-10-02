package com.senai.mycarterinhasenai.app.di

import com.senai.mycarterinhasenai.core.auth.SessionTokenStore
import com.senai.mycarterinhasenai.feature.login.domain.repository.LoginRepository
import com.senai.mycarterinhasenai.feature.unidadecurriculares.domain.repository.UnidadeCurricularRepository


interface AppContainer {
    val sessionTokenStore : SessionTokenStore

    val loginRepository : LoginRepository

    val unidadeCurricularRepository : UnidadeCurricularRepository
}