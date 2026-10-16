package com.senai.mycarterinhasenai.feature.professor.turmas.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.senai.mycarterinhasenai.feature.professor.turmas.domain.model.TurmaProfessor

@Composable
fun TurmaProfessorCard(
    turma: TurmaProfessor,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = turma.codigo,
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                text = "Curso: " + turma.curso,
                style = MaterialTheme.typography.bodyMedium
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Semestre: " + turma.semestre)
                Text("Alunos: " + turma.quantidadeAlunos)
            }
            Text(
                text = "Dias: " + turma.diasSemana.joinToString(", "),
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "Horário: " + turma.horarioInicio + " às " + turma.horarioFim,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}
