package com.senai.mycarterinhasenai.feature.carterinha.presentantion.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.senai.mycarterinhasenai.R

@Composable
fun PefilAluno (
    nome: String,
    curso: String,
    idFoto: Int = R.drawable.loginft
){
    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = idFoto),
            "foto de perfil",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(200.dp)
                .clip(CircleShape)
                .border(
                    width = 2.dp,
                    Color.White,
                    CircleShape
                )
        )

        InfoAluno(
            label = "nome",
            value = nome
        )
        InfoAluno(
            label = "curso",
            value = curso,
            fontSizeValue = 25.sp,

            )


    }
}