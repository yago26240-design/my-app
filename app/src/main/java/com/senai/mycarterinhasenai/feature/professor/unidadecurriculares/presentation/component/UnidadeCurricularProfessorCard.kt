package com.senai.mycarterinhasenai.feature.professor.unidadecurriculares.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.senai.mycarterinhasenai.feature.professor.unidadecurriculares.domain.model.UnidadeCurricularProfessor

@Composable
fun UnidadeCurricularProfessorCard(
    unidadeCurricular: UnidadeCurricularProfessor,
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
                text = unidadeCurricular.nome,
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                text = "Turma: " + unidadeCurricular.turma,
                style = MaterialTheme.typography.bodyMedium
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Carga horária: " + unidadeCurricular.cargaHoraria + " h")
                Text("Aulas: " + unidadeCurricular.aulasRealizadas + "/" +
                    unidadeCurricular.quantidadeAulas)
            }
            val progresso = if (unidadeCurricular.quantidadeAulas > 0) {
                (unidadeCurricular.aulasRealizadas.toFloat() /
                    unidadeCurricular.quantidadeAulas).coerceIn(0f, 1f)
            } else 0f

            LinearProgressIndicator(
                progress = { progresso },
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                text = "Dias: " + unidadeCurricular.diasSemana.joinToString(", "),
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "Horário: " + unidadeCurricular.horarioInicio +
                    " às " + unidadeCurricular.horarioFim,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}
