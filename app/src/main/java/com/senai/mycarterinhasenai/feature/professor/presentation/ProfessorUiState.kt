package com.senai.mycarterinhasenai.feature.professor.presentation
import com.senai.mycarterinhasenai.feature.professor.domain.model.TurmaProfessor
import com.senai.mycarterinhasenai.feature.professor.domain.model.UnidadeCurricularProfessor

data class TurmasProfessorUiState(val isLoading:Boolean=true,val turmas:List<TurmaProfessor> = emptyList(),val errorMessage:String?=null)
data class UcsProfessorUiState(val isLoading:Boolean=true,val unidades:List<UnidadeCurricularProfessor> = emptyList(),val errorMessage:String?=null)
