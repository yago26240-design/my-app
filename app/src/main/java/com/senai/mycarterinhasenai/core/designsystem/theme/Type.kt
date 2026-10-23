package com.senai.mycarterinhasenai.core.designsystem.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight

// Tipografia simples e legível, aplicada globalmente pelo MaterialTheme.
// Mantém a mesma aparência nos perfis Aluno e Professor.
private val base = Typography()
private val fonte = FontFamily.SansSerif

val AppTypography = Typography(
    displayLarge = base.displayLarge.copy(fontFamily = fonte, fontWeight = FontWeight.Bold),
    displayMedium = base.displayMedium.copy(fontFamily = fonte, fontWeight = FontWeight.Bold),
    displaySmall = base.displaySmall.copy(fontFamily = fonte, fontWeight = FontWeight.Bold),
    headlineLarge = base.headlineLarge.copy(fontFamily = fonte, fontWeight = FontWeight.Bold),
    headlineMedium = base.headlineMedium.copy(fontFamily = fonte, fontWeight = FontWeight.Bold),
    headlineSmall = base.headlineSmall.copy(fontFamily = fonte, fontWeight = FontWeight.Bold),
    titleLarge = base.titleLarge.copy(fontFamily = fonte, fontWeight = FontWeight.SemiBold),
    titleMedium = base.titleMedium.copy(fontFamily = fonte, fontWeight = FontWeight.SemiBold),
    titleSmall = base.titleSmall.copy(fontFamily = fonte, fontWeight = FontWeight.SemiBold),
    bodyLarge = base.bodyLarge.copy(fontFamily = fonte),
    bodyMedium = base.bodyMedium.copy(fontFamily = fonte),
    bodySmall = base.bodySmall.copy(fontFamily = fonte),
    labelLarge = base.labelLarge.copy(fontFamily = fonte, fontWeight = FontWeight.Medium),
    labelMedium = base.labelMedium.copy(fontFamily = fonte, fontWeight = FontWeight.Medium),
    labelSmall = base.labelSmall.copy(fontFamily = fonte, fontWeight = FontWeight.Medium)
)
