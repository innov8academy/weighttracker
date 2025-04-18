package com.example.weighttracker.ui.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import com.example.weighttracker.data.local.WeightEntry
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@Composable
fun WeightChart(entries: List<WeightEntry>) {
    if (entries.isEmpty()) return
    val weights = entries.map { it.weight }
    val minWeight = weights.minOrNull() ?: 0f
    val maxWeight = weights.maxOrNull() ?: 0f
    val weightRange = maxWeight - minWeight
    val dates = entries.map { it.date }
    val minDate = dates.minOrNull() ?: return
    val maxDate = dates.maxOrNull() ?: return
    val dateRange = maxDate.toEpochDay() - minDate.toEpochDay()

    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(16.dp)
    ) {
        val width = size.width
        val height = size.height
        val path = Path()

        if (dateRange == 0L) return@Canvas
        
        entries.forEachIndexed { index, entry ->
            val x = if(dateRange > 0) {
                (entry.date.toEpochDay() - minDate.toEpochDay()) / dateRange * width
            } else {
                width/2
            }
            val y = height - ((entry.weight - minWeight) / weightRange * height)
            val offset = Offset(x, y)

            if (index == 0) {
                path.moveTo(offset.x, offset.y)
            } else {
                path.lineTo(offset.x, offset.y)
            }
        }
        
        drawPath(
            path = path,
            color = MaterialTheme.colorScheme.primary,
            style = Stroke(width = 4f)
        )
    }
}