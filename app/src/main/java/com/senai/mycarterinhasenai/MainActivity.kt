package com.senai.mycarterinhasenai

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rafaelcosta.myapplication.QrCode
import com.senai.mycarterinhasenai.ui.theme.MyCarterinhaSenaiTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyCarterinhaSenaiTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CarterinhaDigitalApp(
                        modifier = Modifier.padding(paddingValues = innerPadding) )
                }
            }
        }
    }
}
@Composable
fun CarterinhaDigitalApp(modifier: Modifier = Modifier){
    Box{
        Image(
            painter = painterResource(id = R.drawable.imagemfundo),
             "Fundo",
            modifier= Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround,
            modifier = Modifier.fillMaxSize()
        ){
            Image(
                painter = painterResource(id = R.drawable.logo),
                "logo senai",
                modifier= Modifier.width(300.dp)
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.loginft),
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
                Row() {
                    Text(
                        text = "Nome: ",
                        color = Color.White,
                        fontSize = 25.sp
                    )
                    Text(
                        text = " Yago Silva",
                        color = Color.White,
                        fontSize = 25.sp
                    )
                }
            }

                Row(){
                    Text(
                        text = " Curso: ",
                        color = Color.White,
                        fontSize = 25.sp
                    )
                    Text(
                        text = " Desenvolvimento de Sistemas",
                        color = Color.White,
                        fontSize = 25.sp
                    )
                }

            QrCode(
                conteudo = "9000000001755239490"
            )
        }




    }



}