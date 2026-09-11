package com.senai.mycarterinhasenai.feature.login.data.repository
import com.senai.mycarterinhasenai.feature.login.domain.model.UsuarioLogado
import kotlinx.coroutines.delay

class FakeLoginRepositoryIpl : LoginRepository{

    override suspend fun login(
        usuario: String,
        senha: String
    ): Result<UsuarioLogado>{
        delay(timeMillis = 1500)

        return if(usuario.equals("aluno") && senha.equals("123")){
            Result.success(
                value = UsuarioLogado(
                    id = "1",
                    nome = "yago silva",
                    curso = "DS",
                    turma = "Devest-b",
                    token = "token-fake-para-aula"

                )
            )
        }else {
            Result.failure(
                exception = IllegalArgumentException(
                    "Login ou senha invalidos"
                )
            )
        }
    }
}