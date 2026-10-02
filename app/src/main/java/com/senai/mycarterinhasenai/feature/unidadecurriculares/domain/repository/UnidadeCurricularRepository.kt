package com.senai.mycarterinhasenai.feature.unidadecurriculares.domain.repository

import com.senai.mycarterinhasenai.feature.unidadecurriculares.domain.model.UnidadeCurricular

interface UnidadeCurricularRepository {
    suspend fun listar():Result<List<UnidadeCurricular>>
}