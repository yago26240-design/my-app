package com.senai.mycarterinhasenai.feature.home.presentation.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.senai.mycarterinhasenai.app.navigation.Routes
import com.senai.mycarterinhasenai.core.designsystem.theme.MyCarterinhaSenaiTheme
import com.senai.mycarterinhasenai.feature.home.component.BotaoNavegacao
import com.senai.mycarterinhasenai.feature.login.domain.model.UsuarioLogado

@Composable
fun HomeScreen(
    navController: NavController = NavController(LocalContext.current),
    usuarioLogado: UsuarioLogado,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Olá, ${usuarioLogado.nome}",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceContainer
            )
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = usuarioLogado.curso,
                    style = MaterialTheme.typography.titleMedium
                )

                Text(
                    text = "Turma: ${usuarioLogado.turma}"
                )
                Text(
                    text = "Matrícula: ${usuarioLogado.matricula}"
                )
            }
        }

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(14.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            BotaoNavegacao(
                text = "Carteirinha",
                onClick = {
                    navController.navigate(Routes.Carteirinha.route)
                },
                modifier = Modifier.fillMaxWidth()
            )

            BotaoNavegacao(
                text = "Unidades Curriculares",
                onClick = {
                    navController.navigate(Routes.UCAluno.route)
                },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun HomeScreenPreview() {
    MyCarterinhaSenaiTheme {
        HomeScreen(
            usuarioLogado = UsuarioLogado(
                id = "1",
                nome = "Rafael Costa",
                matricula = "2026000001",
                perfil = "ALUNO",
                curso = "Desenvolvimento de Sistemas",
                turma = "2DEVEST-A",
                token = "token"
            )
        )
    }
}