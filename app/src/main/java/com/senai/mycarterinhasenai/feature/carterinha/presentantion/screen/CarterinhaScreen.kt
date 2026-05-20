package com.senai.mycarterinhasenai.feature.carterinha.presentantion.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.rafaelcosta.myapplication.QrCode
import com.senai.mycarterinhasenai.R
import com.senai.mycarterinhasenai.feature.carterinha.presentantion.component.PefilAluno


@Composable
fun CarterinhaScreen(
    modifier: Modifier = Modifier
){
    Box {
        Image(
            painter = painterResource(id = R.drawable.imagemfundo),
            "Fundo",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround,
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                "logo senai",
                modifier = Modifier.fillMaxWidth(fraction = 0.8f)
            )
            PefilAluno(
                nome = "yago silva",
                curso = "sistema",

                )
            QrCode(
                conteudo = "9000000001755239490"
            )

        }
    }
}
