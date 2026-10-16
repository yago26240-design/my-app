package com.senai.mycarterinhasenai.feature.professor.turmas.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.senai.mycarterinhasenai.feature.professor.turmas.domain.repository.TurmaProfessorRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TurmaProfessorViewModel(
    private val repository: TurmaProfessorRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(TurmaProfessorUiState())
    val uiState: StateFlow<TurmaProfessorUiState> = _uiState.asStateFlow()

    fun carregar() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }
            repository.listar()
                .onSuccess { dados ->
                    _uiState.update { it.copy(isLoading = false, turmas = dados, errorMessage = null) }
                }
                .onFailure { erro ->
                    _uiState.update { it.copy(isLoading = false, errorMessage = erro.message ?: "Erro ao carregar turmas.") }
                }
        }
    }
}
