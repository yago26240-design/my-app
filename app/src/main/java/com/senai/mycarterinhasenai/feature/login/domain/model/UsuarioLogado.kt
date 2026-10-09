package com.senai.mycarterinhasenai.feature.login.domain.model

data class UsuarioLogado(
    val id: String,
    val nome: String,
    val matricula: String,
    val perfil: String,
    val curso: String,
    val turma: String,
    val token: String
)