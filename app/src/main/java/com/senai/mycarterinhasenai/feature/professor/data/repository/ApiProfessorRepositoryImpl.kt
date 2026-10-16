package com.senai.mycarterinhasenai.feature.professor.data.repository

import android.util.Log
import com.senai.mycarterinhasenai.feature.professor.data.remote.service.ProfessorApi
import com.senai.mycarterinhasenai.feature.professor.domain.model.TurmaProfessor
import com.senai.mycarterinhasenai.feature.professor.domain.model.UnidadeCurricularProfessor
import com.senai.mycarterinhasenai.feature.professor.domain.repository.ProfessorRepository
import java.io.IOException
import kotlinx.coroutines.CancellationException
import retrofit2.HttpException

class ApiProfessorRepositoryImpl(
    private val api: ProfessorApi
) : ProfessorRepository {

    override suspend fun listarTurmas(): Result<List<TurmaProfessor>> {
        return try {
            val turmas = api.listarTurmas().map { it.toDomain() }
            Log.d("ProfessorAPI", "Turmas recebidas (${turmas.size}): " +
                turmas.joinToString(limit = 5) { it.codigo })
            Result.success(turmas)
        } catch (cancelamento: CancellationException) {
            throw cancelamento
        } catch (erro: Exception) {
            Result.failure(mapearErro(erro))
        }
    }

    override suspend fun listarUnidadesCurriculares(): Result<List<UnidadeCurricularProfessor>> {
        return try {
            val unidades = api.listarUnidadesCurriculares().map { it.toDomain() }
            Log.d("ProfessorAPI", "UCs recebidas (${unidades.size}): " +
                unidades.joinToString(limit = 5) { "${it.nome} / ${it.turma}" })
            Result.success(unidades)
        } catch (cancelamento: CancellationException) {
            throw cancelamento
        } catch (erro: Exception) {
            Result.failure(mapearErro(erro))
        }
    }

    private fun mapearErro(erro: Exception): Exception = when (erro) {
        is HttpException -> when (erro.code()) {
            401 -> IllegalStateException("Sessão expirada. Saia e entre novamente.")
            403 -> IllegalStateException("Seu usuário não tem permissão de Professor.")
            404 -> IllegalStateException("Endpoint do Professor não encontrado na API.")
            else -> IllegalStateException("Erro HTTP ${erro.code()} ao consultar a API.")
        }
        is IOException -> IllegalStateException(
            "Falha de conexão. Confira se a API está iniciada e acessível no emulador."
        )
        else -> IllegalStateException(erro.message ?: "Não foi possível carregar os dados.")
    }
}
