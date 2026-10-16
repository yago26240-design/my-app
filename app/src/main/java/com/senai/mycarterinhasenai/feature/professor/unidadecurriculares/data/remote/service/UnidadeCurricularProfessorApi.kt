package com.senai.mycarterinhasenai.feature.professor.unidadecurriculares.data.remote.service

import com.senai.mycarterinhasenai.feature.professor.unidadecurriculares.data.remote.dto.UnidadeCurricularProfessorDto
import retrofit2.http.GET

interface UnidadeCurricularProfessorApi {
    @GET("professores/me/unidades-curriculares")
    suspend fun listar(): List<UnidadeCurricularProfessorDto>
}
