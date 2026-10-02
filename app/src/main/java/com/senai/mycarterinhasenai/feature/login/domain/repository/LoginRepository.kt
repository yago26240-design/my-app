package com.senai.mycarterinhasenai.feature.login.domain.repository

import com.senai.mycarterinhasenai.feature.login.domain.model.UsuarioLogado


interface LoginRepository {
    suspend fun login(login: String, senha: String): Result<UsuarioLogado>
}