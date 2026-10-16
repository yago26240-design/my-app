package com.senai.mycarterinhasenai.feature.professor.unidadecurriculares.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.senai.mycarterinhasenai.feature.professor.unidadecurriculares.domain.repository.UnidadeCurricularProfessorRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class UnidadeCurricularProfessorViewModel(
    private val repository: UnidadeCurricularProfessorRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(UnidadeCurricularProfessorUiState())
    val uiState: StateFlow<UnidadeCurricularProfessorUiState> = _uiState.asStateFlow()

    fun carregar() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }
            repository.listar()
                .onSuccess { dados ->
                    _uiState.update { it.copy(isLoading = false, unidades = dados, errorMessage = null) }
                }
                .onFailure { erro ->
                    _uiState.update { it.copy(isLoading = false, errorMessage = erro.message ?: "Erro ao carregar unidades curriculares.") }
                }
        }
    }
}
