package com.senai.mycarterinhasenai.feature.professor.presentation.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.senai.mycarterinhasenai.feature.professor.presentation.ProfessorViewModel
import com.senai.mycarterinhasenai.feature.professor.domain.model.TurmaProfessor
import com.senai.mycarterinhasenai.feature.professor.domain.model.UnidadeCurricularProfessor

@Composable
fun TurmasProfessorScreen(viewModel:ProfessorViewModel,modifier:Modifier=Modifier){
 val state by viewModel.turmasState.collectAsStateWithLifecycle()
 LaunchedEffect(viewModel){viewModel.carregarTurmas()}
 when {
  state.isLoading -> Indicador(modifier)
  state.errorMessage!=null -> Aviso(state.errorMessage!!,modifier,viewModel::carregarTurmas)
  state.turmas.isEmpty() -> Aviso("Nenhuma turma vinculada ao professor.",modifier)
  else -> LazyColumn(modifier=modifier.fillMaxSize(),contentPadding=PaddingValues(16.dp),verticalArrangement=Arrangement.spacedBy(16.dp)){
   item{Text("Minhas turmas (${state.turmas.size})",style=MaterialTheme.typography.headlineSmall)}
   items(state.turmas,key={it.id}){TurmaCard(it)}
  }
 }
}

@Composable
fun UcsProfessorScreen(viewModel:ProfessorViewModel,modifier:Modifier=Modifier){
 val state by viewModel.ucsState.collectAsStateWithLifecycle()
 LaunchedEffect(viewModel){viewModel.carregarUcs()}
 when {
  state.isLoading -> Indicador(modifier)
  state.errorMessage!=null -> Aviso(state.errorMessage!!,modifier,viewModel::carregarUcs)
  state.unidades.isEmpty() -> Aviso("Nenhuma unidade curricular vinculada ao professor.",modifier)
  else -> LazyColumn(modifier=modifier.fillMaxSize(),contentPadding=PaddingValues(16.dp),verticalArrangement=Arrangement.spacedBy(16.dp)){
   item{Text("UCs que ministro (${state.unidades.size})",style=MaterialTheme.typography.headlineSmall)}
   items(state.unidades,key={it.id}){UcCard(it)}
  }
 }
}

@Composable
private fun Indicador(modifier:Modifier){Box(modifier.fillMaxSize(),contentAlignment=Alignment.Center){CircularProgressIndicator()}}

@Composable
private fun Aviso(mensagem:String,modifier:Modifier,onRetry:(()->Unit)?=null){
 Column(modifier.fillMaxSize().padding(24.dp),horizontalAlignment=Alignment.CenterHorizontally,verticalArrangement=Arrangement.Center){
  Text(mensagem,color=if(onRetry!=null) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.onSurface)
  if(onRetry!=null){Spacer(Modifier.height(16.dp));Button(onClick=onRetry){Text("Tentar novamente")}}
 }
}

@Composable
private fun CartaoProfessor(titulo:String,subtitulo:String,conteudo:@Composable ColumnScope.()->Unit){
 Card(modifier=Modifier.fillMaxWidth(),shape=RoundedCornerShape(20.dp),colors=CardDefaults.cardColors(containerColor=MaterialTheme.colorScheme.surfaceContainer)){
  Column(Modifier.padding(20.dp),verticalArrangement=Arrangement.spacedBy(10.dp)){
   Text(titulo,style=MaterialTheme.typography.titleLarge,fontWeight=FontWeight.Bold,color=MaterialTheme.colorScheme.primary)
   Text(subtitulo,style=MaterialTheme.typography.titleMedium)
   HorizontalDivider()
   conteudo()
  }
 }
}
private fun dias(lista:List<String>):String=lista.joinToString(", "){it.lowercase().replaceFirstChar { c -> c.uppercase() }}.ifBlank{"Não informados"}

@Composable
private fun TurmaCard(t:TurmaProfessor){
 CartaoProfessor(t.codigo,t.curso){
  Text("Semestre: ${t.semestre} • Alunos: ${t.quantidadeAlunos}")
  Text("Dias: ${dias(t.diasSemana)}")
  Text("Horário: ${t.horarioInicio} às ${t.horarioFim}")
 }
}

@Composable
private fun UcCard(u:UnidadeCurricularProfessor){
 CartaoProfessor(u.nome,"Turma: ${u.turma}"){
  Text("Carga horária: ${u.cargaHoraria} h")
  Text("Aulas realizadas: ${u.aulasRealizadas} de ${u.quantidadeAulas}")
  val progresso=if(u.quantidadeAulas>0) (u.aulasRealizadas.toFloat()/u.quantidadeAulas).coerceIn(0f,1f) else 0f
  LinearProgressIndicator(progress={progresso},modifier=Modifier.fillMaxWidth())
  Text("Dias: ${dias(u.diasSemana)}")
  Text("Horário: ${u.horarioInicio} às ${u.horarioFim}")
 }
}
