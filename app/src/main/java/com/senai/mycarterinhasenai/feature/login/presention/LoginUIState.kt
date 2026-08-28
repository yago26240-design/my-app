package com.senai.mycarterinhasenai.feature.login.presention

import com.senai.mycarterinhasenai.feature.login.domain.model.UsuarioLogado

data class LoginUIState (
     val usuario: String= "",
     val senha: String = "",
     val erroMessage: String? = null,
     val isLoadin: Boolean = false,
     val usuarioLogado: UsuarioLogado? = null
) {
    val loginRealizado: Boolean
        get() = usuarioLogado != null
}