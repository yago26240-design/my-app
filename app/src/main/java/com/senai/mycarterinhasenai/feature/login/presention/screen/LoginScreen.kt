package com.senai.mycarterinhasenai.feature.login.presention.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.senai.mycarterinhasenai.app.navigation.Routes
import com.senai.mycarterinhasenai.feature.login.domain.model.UsuarioLogado
import com.senai.mycarterinhasenai.feature.login.presention.LoginEvent
import com.senai.mycarterinhasenai.feature.login.presention.LoginViewModel

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    navController: NavController = NavController(LocalContext.current),
 viewModel: LoginViewModel = viewModel(),
    onLoginSuceso:(UsuarioLogado)-> Unit = {}
)

{
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(uiState.usuarioLogado) {
        uiState.usuarioLogado?.let{
            usuario ->
            viewModel.onEvent(LoginEvent.OnNavegacaoRealizada)
            onLoginSuceso(usuario)
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(
            10.dp,
            Alignment.CenterVertically
        ),
        modifier = Modifier.fillMaxSize()
    ) {

        Text(
            text = "LOGIN",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )

        TextField(
            value = uiState.usuario,
            onValueChange = { value ->
                viewModel.onEvent(LoginEvent.OnUsuarioChange(value))
            },
            label = {
                Text(
                    text= "Email")
            }
        )

        OutlinedTextField(
            value = uiState.senha,
            onValueChange = {  value ->
                viewModel.onEvent(LoginEvent.OnSenhaChange(value))
    },

            label = {
                Text(
                    text = "Senha")


            }
        )

        Button(
            onClick = {
                viewModel.onEvent(LoginEvent.OnEntrarClick)
            },
            shape = RoundedCornerShape(4.dp),
            border = BorderStroke(
                width = 2.dp,
                color = MaterialTheme.colorScheme.primary
            ),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary
            ),
            modifier = Modifier.fillMaxWidth(0.6f)
        ) {
            Text("Entrar")
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun LoginScreenPreview() {
    LoginScreen()
}