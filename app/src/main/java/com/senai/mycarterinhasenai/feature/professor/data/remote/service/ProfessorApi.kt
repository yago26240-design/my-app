package com.senai.mycarterinhasenai.feature.professor.data.remote.service

import com.senai.mycarterinhasenai.feature.professor.data.remote.dto.TurmaProfessorResponseDto
import com.senai.mycarterinhasenai.feature.professor.data.remote.dto.UnidadeCurricularProfessorResponseDto
import retrofit2.http.GET

interface ProfessorApi {
    @GET("professores/me/turmas")
    suspend fun listarTurmas(): List<TurmaProfessorResponseDto>

    @GET("professores/me/unidades-curriculares")
    suspend fun listarUnidadesCurriculares(): List<UnidadeCurricularProfessorResponseDto>
}
