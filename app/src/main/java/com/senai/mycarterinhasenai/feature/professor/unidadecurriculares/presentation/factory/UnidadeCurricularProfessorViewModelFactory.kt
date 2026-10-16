package com.senai.mycarterinhasenai.feature.professor.unidadecurriculares.presentation.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.senai.mycarterinhasenai.feature.professor.unidadecurriculares.domain.repository.UnidadeCurricularProfessorRepository
import com.senai.mycarterinhasenai.feature.professor.unidadecurriculares.presentation.UnidadeCurricularProfessorViewModel

class UnidadeCurricularProfessorViewModelFactory(
    private val repository: UnidadeCurricularProfessorRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UnidadeCurricularProfessorViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return UnidadeCurricularProfessorViewModel(repository) as T
        }
        throw IllegalArgumentException("ViewModel desconhecido: " + modelClass.name)
    }
}
