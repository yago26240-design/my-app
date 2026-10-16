package com.senai.mycarterinhasenai.feature.professor.unidadecurriculares.data.repository

import com.senai.mycarterinhasenai.feature.professor.unidadecurriculares.data.remote.service.UnidadeCurricularProfessorApi
import com.senai.mycarterinhasenai.feature.professor.unidadecurriculares.domain.model.UnidadeCurricularProfessor
import com.senai.mycarterinhasenai.feature.professor.unidadecurriculares.domain.repository.UnidadeCurricularProfessorRepository
import java.io.IOException
import kotlinx.coroutines.CancellationException
import retrofit2.HttpException

class ApiUnidadeCurricularProfessorRepositoryImpl(
    private val api: UnidadeCurricularProfessorApi
) : UnidadeCurricularProfessorRepository {

    override suspend fun listar(): Result<List<UnidadeCurricularProfessor>> {
        return try {
            Result.success(api.listar().map { it.toDomain() })
        } catch (ex: CancellationException) {
            throw ex
        } catch (ex: Exception) {
            Result.failure(mapearErro(ex))
        }
    }

    private fun mapearErro(ex: Exception): Exception {
        return when (ex) {
            is HttpException -> when (ex.code()) {
                401 -> IllegalStateException("Sessão inválida. Faça login novamente.")
                403 -> IllegalStateException("Você não tem permissão para consultar unidades curriculares.")
                else -> IllegalStateException("Erro ao carregar unidades curriculares (HTTP " + ex.code() + ").")
            }
            is IOException -> IllegalStateException("Não foi possível conectar à API. Verifique se ela está rodando.")
            else -> IllegalStateException(ex.message ?: "Erro ao carregar unidades curriculares.")
        }
    }
}
