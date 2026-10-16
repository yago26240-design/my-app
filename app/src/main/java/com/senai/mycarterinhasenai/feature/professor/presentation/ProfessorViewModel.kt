package com.senai.mycarterinhasenai.feature.professor.presentation
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.senai.mycarterinhasenai.feature.professor.domain.repository.ProfessorRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProfessorViewModel(private val repository:ProfessorRepository):ViewModel(){
 private val _turmasState=MutableStateFlow(TurmasProfessorUiState())
 val turmasState=_turmasState.asStateFlow()
 private val _ucsState=MutableStateFlow(UcsProfessorUiState())
 val ucsState=_ucsState.asStateFlow()
 fun carregarTurmas(){
  viewModelScope.launch {
   _turmasState.value=TurmasProfessorUiState()
   repository.listarTurmas().onSuccess { _turmasState.value=TurmasProfessorUiState(false,it) }
    .onFailure { _turmasState.value=TurmasProfessorUiState(false,errorMessage=it.message ?: "Erro ao carregar turmas.") }
  }
 }
 fun carregarUcs(){
  viewModelScope.launch {
   _ucsState.value=UcsProfessorUiState()
   repository.listarUnidadesCurriculares().onSuccess { _ucsState.value=UcsProfessorUiState(false,it) }
    .onFailure { _ucsState.value=UcsProfessorUiState(false,errorMessage=it.message ?: "Erro ao carregar UCs.") }
  }
 }
}
