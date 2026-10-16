package com.senai.mycarterinhasenai.feature.professor.unidadecurriculares.presentation

import com.senai.mycarterinhasenai.feature.professor.unidadecurriculares.domain.model.UnidadeCurricularProfessor

data class UnidadeCurricularProfessorUiState(
    val isLoading: Boolean = false,
    val unidades: List<UnidadeCurricularProfessor> = emptyList(),
    val errorMessage: String? = null
)
