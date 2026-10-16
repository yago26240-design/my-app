package com.senai.mycarterinhasenai.app.di

import com.senai.mycarterinhasenai.core.auth.SessionTokenStore
import com.senai.mycarterinhasenai.feature.login.domain.repository.LoginRepository
import com.senai.mycarterinhasenai.feature.unidadecurriculares.domain.repository.UnidadeCurricularRepository
import com.senai.mycarterinhasenai.feature.professor.turmas.domain.repository.TurmaProfessorRepository
import com.senai.mycarterinhasenai.feature.professor.unidadecurriculares.domain.repository.UnidadeCurricularProfessorRepository


interface AppContainer {
    val sessionTokenStore : SessionTokenStore

    val loginRepository : LoginRepository

    val unidadeCurricularRepository : UnidadeCurricularRepository

    val turmaProfessorRepository : TurmaProfessorRepository

    val unidadeCurricularProfessorRepository : UnidadeCurricularProfessorRepository
}