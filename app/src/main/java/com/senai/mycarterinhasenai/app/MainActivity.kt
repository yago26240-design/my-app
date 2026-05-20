package com.senai.mycarterinhasenai.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.rafaelcosta.myapplication.QrCode
import com.senai.mycarterinhasenai.R
import com.senai.mycarterinhasenai.core.designsystem.theme.MyCarterinhaSenaiTheme
import com.senai.mycarterinhasenai.feature.carterinha.presentantion.component.PefilAluno
import com.senai.mycarterinhasenai.feature.carterinha.presentantion.screen.CarterinhaScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyCarterinhaSenaiTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CarterinhaScreen(
                        modifier = Modifier.padding(paddingValues = innerPadding) )
                }
            }
        }
    }
}



