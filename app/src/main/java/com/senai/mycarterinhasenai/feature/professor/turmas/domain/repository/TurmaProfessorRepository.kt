package com.senai.mycarterinhasenai.feature.professor.turmas.domain.repository

import com.senai.mycarterinhasenai.feature.professor.turmas.domain.model.TurmaProfessor

interface TurmaProfessorRepository {
    suspend fun listar(): Result<List<TurmaProfessor>>
}
