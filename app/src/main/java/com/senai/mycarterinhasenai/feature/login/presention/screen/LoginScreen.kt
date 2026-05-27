package com.senai.mycarterinhasenai.feature.login.presention.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Label
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LoginSreen(modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement
            .spacedBy(10.dp, Alignment.CenterVertically),
        modifier = Modifier.fillMaxSize()

    ){
        Text(
            text = "Login",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )
        TextField(
            value = "",
            onValueChange = {},
            label =  {
                Text(
                    text = "Email"
                )
            }
        )
        OutlinedTextField(
            value = "",
            onValueChange = {},
            label =  {
                Text(
                    text = "Senha"
                )

            }
        )
        Button(
            onClick = {},
            shape = RoundedCornerShape(size = 4.dp),
            border = BorderStroke(
                width = 5.dp,
            color = MaterialTheme.colorScheme.primary
            ),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary
            ),
            modifier = Modifier
                .fillMaxWidth(fraction = .6f)


        ) {
            Text(
                text = "Entrar",

            )


        }


    }

}
@Preview(showBackground = true,
    showSystemUi = true
    )
@Composable
fun LoginScreenPreview(){
    LoginSreen()
}