package com.senai.mycarterinhasenai.feature.professor.domain.repository

import com.senai.mycarterinhasenai.feature.professor.domain.model.TurmaProfessor
import com.senai.mycarterinhasenai.feature.professor.domain.model.UnidadeCurricularProfessor

interface ProfessorRepository {
    suspend fun listarTurmas(): Result<List<TurmaProfessor>>
    suspend fun listarUnidadesCurriculares(): Result<List<UnidadeCurricularProfessor>>
}
