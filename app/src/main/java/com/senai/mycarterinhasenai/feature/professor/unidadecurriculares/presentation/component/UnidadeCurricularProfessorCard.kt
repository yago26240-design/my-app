package com.senai.mycarterinhasenai.feature.professor.unidadecurriculares.presentation.component

import androidx.compose.foundation.BorderStroke
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
    val progresso = if (unidadeCurricular.quantidadeAulas > 0) {
        (unidadeCurricular.aulasRealizadas.toFloat() / unidadeCurricular.quantidadeAulas)
            .coerceIn(0f, 1f)
    } else {
        0f
    }

    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(18.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text(
                text = unidadeCurricular.nome,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary
            )
            TextoInfoProfessor("Turma", unidadeCurricular.turma)
            TextoInfoProfessor("Carga horária", "${unidadeCurricular.cargaHoraria} h")
            TextoInfoProfessor(
                "Aulas",
                "${unidadeCurricular.aulasRealizadas}/${unidadeCurricular.quantidadeAulas}"
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    text = "${(progresso * 100).toInt()}%",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.primary
                )
            }
            LinearProgressIndicator(
                progress = { progresso },
                modifier = Modifier.fillMaxWidth(),
                color = MaterialTheme.colorScheme.primary,
                trackColor = MaterialTheme.colorScheme.surfaceContainerHigh
            )
            TextoInfoProfessor("Dias", unidadeCurricular.diasSemana.joinToString(", "))
            TextoInfoProfessor(
                "Horário",
                "${unidadeCurricular.horarioInicio} às ${unidadeCurricular.horarioFim}"
            )
        }
    }
}

@Composable
private fun TextoInfoProfessor(rotulo: String, valor: String) {
    Text(
        text = "${rotulo}: ${valor}",
        style = MaterialTheme.typography.bodyMedium,
        color = MaterialTheme.colorScheme.onSurfaceVariant
    )
}
