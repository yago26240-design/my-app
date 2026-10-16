package com.senai.mycarterinhasenai.feature.professor.data.remote.dto

import com.senai.mycarterinhasenai.feature.professor.domain.model.TurmaProfessor
import kotlinx.serialization.Serializable

@Serializable
data class TurmaProfessorResponseDto(
    val id: String,
    val codigo: String,
    val curso: String,
    val semestre: String,
    val quantidadeAlunos: Int,
    val diasSemana: List<String>,
    val horarioInicio: String,
    val horarioFim: String
) {
    fun toDomain() = TurmaProfessor(
        id = id,
        codigo = codigo,
        curso = curso,
        semestre = semestre,
        quantidadeAlunos = quantidadeAlunos,
        diasSemana = diasSemana,
        horarioInicio = horarioInicio,
        horarioFim = horarioFim
    )
}
