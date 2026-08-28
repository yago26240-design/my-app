package com.senai.mycarterinhasenai.feature.login.data.repository

import com.senai.mycarterinhasenai.feature.login.domain.model.UsuarioLogado

interface LoginRepository {
    suspend fun Login( usuario: String, senha: String): Result <UsuarioLogado>
}