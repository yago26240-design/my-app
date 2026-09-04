package com.senai.mycarterinhasenai.feature.login.presention

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.senai.mycarterinhasenai.feature.login.data.repository.FakeLoginRepositoryIpl
import com.senai.mycarterinhasenai.feature.login.data.repository.LoginRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(
    private val repository: LoginRepository = FakeLoginRepositoryIpl()
): ViewModel() {

    private val _uiState = MutableStateFlow(value = LoginUIState())
    val uiState: StateFlow<LoginUIState> = _uiState.asStateFlow()

    fun onEvent(event: LoginEvent){
        when(event) {
            is LoginEvent.OnUsuarioChange -> {
                _uiState.update { state ->
                    state.copy(
                        usuario = event.value,
                        erroMessage = null
                    )
                }
            }

            is LoginEvent.OnSenhaChange -> {
                _uiState.update { state ->
                    state.copy(
                        senha = event.value,
                        erroMessage = null
                    )
                }
            }

            LoginEvent.OnNevegacaoRealizada -> {
                _uiState.update {
                    it.copy(
                        usuarioLogado = null
                    )
                }
            }
            LoginEvent.OnEntrarClick -> fazerLogin()
        }
    }
    private fun fazerLogin() {
     val state = _uiState.value

        if(state.usuario.isBlank() || state.senha.isBlank()){
            _uiState.update {
                it.copy(
                    erroMessage = "preencha login e senha"
                )
            }
            return
        }

        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    isLoadin = true,
                    erroMessage = null,
                    usuarioLogado = null
                )
            }
            val result = repository.Login(
                usuario = state.usuario.trim(),
                senha = state.senha.trim()
            )

            result
                .onSuccess{ usuarioLogado ->
                    _uiState.update {
                        it.copy(
                            isLoadin = false,
                            erroMessage = null,
                            usuarioLogado = usuarioLogado
                        )
                    }
                }
                .onFailure { throwable ->
                    _uiState.update {
                        it.copy(
                            isLoadin = false,
                            erroMessage = throwable.message ?:"erro ao fazer Login"
                        )
                    }
                }

        }
         }
}