package com.example.weighttracker.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weighttracker.data.local.WeightEntry
import com.example.weighttracker.util.MotivationalMessages

@Composable
fun DashboardScreen(
    vm: DashboardViewModel,
    onAdd: () -> Unit,
    onHistory: () -> Unit,
    onSettings: () -> Unit
) {
    val entries by vm.entries.collectAsState()
    val prefs by vm.prefs.collectAsState()
    val progress = remember(entries, prefs) {
        if (prefs.goal == 0f) 0f else when (prefs.goalType) {
            "Cut" -> ((prefs.current - (entries.lastOrNull()?.weight ?: prefs.current)) / (prefs.current - prefs.goal)).coerceIn(0f, 1f)
            "Bulk" -> (((entries.lastOrNull()?.weight ?: prefs.current) - prefs.current) / (prefs.goal - prefs.current)).coerceIn(0f, 1f)
            else -> 0f
        }
    }
    Scaffold(floatingActionButton = { FloatingActionButton(onClick = onAdd) { Text("+") } }) { padding ->
        Column(Modifier.padding(padding).fillMaxSize()) {
            Text("Progress", fontSize = 22.sp, fontWeight = FontWeight.Bold)
            LinearProgressIndicator(progress = progress, Modifier.fillMaxWidth())
            Spacer(Modifier.height(16.dp))
            WeightChart(entries = entries)
            Spacer(Modifier.height(16.dp))
            Text(MotivationalMessages.randomMessage(prefs.goalType), fontSize = 18.sp)
            Spacer(Modifier.weight(1f))
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                Button(onClick = onHistory) { Text("History") }
                Button(onClick = onSettings) { Text("Settings") }
            }
        }
    }
}

@Composable
fun WeightChart(entries: List<WeightEntry>) {
    // Simple Compose Canvas line chart for offline use.
    // Due to space, implementation is omitted; draw lines based on weight by date.
    Box(Modifier.height(180.dp).fillMaxWidth(), contentAlignment = Alignment.Center) {
        Text("[Trend Graph]")
    }
}