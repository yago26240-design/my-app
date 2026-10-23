package com.senai.mycarterinhasenai.feature.carterinha.presentantion.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.senai.mycarterinhasenai.R
import com.senai.mycarterinhasenai.feature.carterinha.presentantion.component.PerfilAluno
import com.senai.mycarterinhasenai.feature.login.domain.model.UsuarioLogado
import com.senai.mycarterinhasenai.myapplication.QrCode

@Composable
fun CarteirinhaScreen(
    usuarioLogado: UsuarioLogado,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        // O mesmo papel de fundo original, agora sobre as cores do MaterialTheme.
        Image(
            painter = painterResource(id = R.drawable.imagemfundo),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .alpha(0.35f),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp, vertical = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(
                space = 18.dp,
                alignment = Alignment.CenterVertically
            )
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo SENAI",
                modifier = Modifier.width(215.dp),
                contentScale = ContentScale.Fit
            )

            PerfilAluno(
                nome = usuarioLogado.nome,
                matricula = usuarioLogado.matricula,
                curso = usuarioLogado.curso
            )

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(
                    text = "Identificação digital",
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onBackground
                )
                // Mantém o QR Code existente, gerado a partir da matrícula real.
                QrCode(
                    conteudo = usuarioLogado.matricula,
                    modifier = Modifier.size(160.dp)
                )
            }
        }
    }
}
