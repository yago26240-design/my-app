package com.senai.mycarterinhasenai.feature.professor.unidadecurriculares.domain.repository

import com.senai.mycarterinhasenai.feature.professor.unidadecurriculares.domain.model.UnidadeCurricularProfessor

interface UnidadeCurricularProfessorRepository {
    suspend fun listar(): Result<List<UnidadeCurricularProfessor>>
}
