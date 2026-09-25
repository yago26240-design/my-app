package com.senai.mycarterinhasenai.feature.unidadecurriculares.presentation

import com.senai.mycarterinhasenai.app.navigation.Routes
import com.senai.mycarterinhasenai.feature.unidadecurriculares.domain.model.UnidadeCurricular

data class UnidadeCurricularUiState(
    val listaUnidadesCurriculares: List<UnidadeCurricular> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
) {
}