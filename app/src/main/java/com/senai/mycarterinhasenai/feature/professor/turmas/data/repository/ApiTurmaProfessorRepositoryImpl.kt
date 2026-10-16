package com.senai.mycarterinhasenai.feature.professor.turmas.data.repository

import com.senai.mycarterinhasenai.feature.professor.turmas.data.remote.service.TurmaProfessorApi
import com.senai.mycarterinhasenai.feature.professor.turmas.domain.model.TurmaProfessor
import com.senai.mycarterinhasenai.feature.professor.turmas.domain.repository.TurmaProfessorRepository
import java.io.IOException
import kotlinx.coroutines.CancellationException
import retrofit2.HttpException

class ApiTurmaProfessorRepositoryImpl(
    private val api: TurmaProfessorApi
) : TurmaProfessorRepository {

    override suspend fun listar(): Result<List<TurmaProfessor>> {
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
                403 -> IllegalStateException("Você não tem permissão para consultar turmas.")
                else -> IllegalStateException("Erro ao carregar turmas (HTTP " + ex.code() + ").")
            }
            is IOException -> IllegalStateException("Não foi possível conectar à API. Verifique se ela está rodando.")
            else -> IllegalStateException(ex.message ?: "Erro ao carregar turmas.")
        }
    }
}
