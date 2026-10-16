package com.senai.mycarterinhasenai.feature.professor.presentation.factory
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.senai.mycarterinhasenai.feature.professor.domain.repository.ProfessorRepository
import com.senai.mycarterinhasenai.feature.professor.presentation.ProfessorViewModel

class ProfessorViewModelFactory(private val repository:ProfessorRepository):ViewModelProvider.Factory{
 override fun <T:ViewModel> create(modelClass:Class<T>):T {
  if(modelClass.isAssignableFrom(ProfessorViewModel::class.java)){
   @Suppress("UNCHECKED_CAST")
   return ProfessorViewModel(repository) as T
  }
  throw IllegalArgumentException("ViewModel não suportado")
 }
}
