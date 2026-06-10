package com.senai.mycarterinhasenai.feature.unidadecurriculares.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.senai.mycarterinhasenai.feature.unidadecurriculares.domain.model.UnidadeCurricular
import com.senai.mycarterinhasenai.feature.unidadecurriculares.presentation.screen.UnidadeCurricularScreen

@Composable
fun UnidadeCurricularCard(
    modifier: Modifier = Modifier,
    unidadeCurricular: UnidadeCurricular
){
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(3.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(text = unidadeCurricular.nome,
                style = MaterialTheme.typography.titleLarge

            )
            Text(text = "Professor:${unidadeCurricular.professor}",
                style = MaterialTheme.typography.bodyMedium

                )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "N1: ${unidadeCurricular.nota1}")
                Text(text = "N2: ${unidadeCurricular.nota2}")
                Text(text = "media:${unidadeCurricular.media}")
            }

                Text(text = "faltas: ${unidadeCurricular.faltas}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.primary
                )

        }
    }
}
@Preview(
    showBackground = true
)
@Composable
fun UnidadeCurricularCardPreview(){
    UnidadeCurricularCard(
        unidadeCurricular = UnidadeCurricular(
            id = "1",
            nome = "Matematica",
            professor = "Dr. Yagox",
            nota1 = 5.6,
            nota2 = 9.8,
            media = 7.5,
            faltas = 2
        )
    )
}
