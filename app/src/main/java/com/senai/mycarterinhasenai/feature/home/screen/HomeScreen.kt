package com.senai.mycarterinhasenai.feature.home.presentation.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.senai.mycarterinhasenai.app.navigation.Routes
import com.senai.mycarterinhasenai.feature.home.component.BotaoNavegacao
import com.senai.mycarterinhasenai.feature.login.domain.model.UsuarioLogado

@Composable
fun HomeScreen(navController:NavController,usuarioLogado:UsuarioLogado,modifier:Modifier=Modifier){
 val professor=usuarioLogado.perfil.equals("PROFESSOR",ignoreCase=true)
 Column(modifier.fillMaxSize().padding(24.dp),verticalArrangement=Arrangement.spacedBy(22.dp),horizontalAlignment=Alignment.CenterHorizontally){
  Text("Olá, ${usuarioLogado.nome}",style=MaterialTheme.typography.headlineSmall,fontWeight=FontWeight.Bold)
  Card(modifier=Modifier.fillMaxWidth(),colors=CardDefaults.cardColors(containerColor=MaterialTheme.colorScheme.surfaceContainer)){
   Column(Modifier.padding(20.dp),verticalArrangement=Arrangement.spacedBy(8.dp)){
    Text(if(professor)"Área do Professor" else usuarioLogado.curso,style=MaterialTheme.typography.titleMedium)
    if(!professor) Text("Turma: ${usuarioLogado.turma}")
    Text("Matrícula: ${usuarioLogado.matricula}")
   }
  }
  if(professor){
   BotaoNavegacao("Minhas Turmas",{navController.navigate(Routes.TurmasProfessor.route)},Modifier.fillMaxWidth())
   BotaoNavegacao("Unidades Curriculares",{navController.navigate(Routes.UCProfessor.route)},Modifier.fillMaxWidth())
  } else {
   BotaoNavegacao("Carteirinha",{navController.navigate(Routes.Carteirinha.route)},Modifier.fillMaxWidth())
   BotaoNavegacao("Unidades Curriculares",{navController.navigate(Routes.UCAluno.route)},Modifier.fillMaxWidth())
  }
 }
}
