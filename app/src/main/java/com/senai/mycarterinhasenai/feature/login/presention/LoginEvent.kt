package com.senai.mycarterinhasenai.feature.login.presention

sealed interface LoginEvent {
    data class OnUsuarioChange(val value: String): LoginEvent
    data class OnSenhaChange(val value: String): LoginEvent
    data object OnEntrarClick: LoginEvent
    data object OnNavegacaoRealizada: LoginEvent
}