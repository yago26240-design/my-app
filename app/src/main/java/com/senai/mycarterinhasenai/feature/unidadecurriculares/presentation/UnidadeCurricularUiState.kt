package com.senai.mycarterinhasenai.feature.unidadecurriculares.presentation

import com.senai.mycarterinhasenai.app.navigation.Routes
import com.senai.mycarterinhasenai.feature.unidadecurriculares.domain.model.UnidadeCurricular

data class UnidadeCurricularUiState(
    val isLoading: Boolean = false,
    val unidades: List<UnidadeCurricular> = emptyList(),
    val errorMessage: String? = null
)