package com.example.weighttracker.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.FilterChip
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun OnboardingScreen(vm: OnboardingViewModel, onDone: () -> Unit) {
    var goalType by remember { mutableStateOf("Cut") }
    var current by remember { mutableStateOf(0f) }
    var goal by remember { mutableStateOf(0f) }

    Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text("Choose your goal", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        GoalTypeChips(selected = goalType, onSelected = { goalType = it })
        OutlinedTextField(value = if (current == 0f) "" else current.toString(), onValueChange = { current = it.toFloatOrNull() ?: 0f }, label = { Text("Current Weight (kg)") })
        OutlinedTextField(value = if (goal == 0f) "" else goal.toString(), onValueChange = { goal = it.toFloatOrNull() ?: 0f }, label = { Text("Goal Weight (kg)") })
        Button(onClick = { vm.saveGoal(goalType, current, goal); onDone() }) { Text("Continue") }
    }
}

@Composable
private fun GoalTypeChips(selected: String, onSelected: (String) -> Unit) {
    Row(Modifier.padding(vertical = 16.dp)) {
        listOf("Cut", "Bulk", "Maintain").forEach { type ->
            FilterChip(selected = selected == type, onClick = { onSelected(type) }) { Text(type) }
            Spacer(Modifier.width(8.dp))
        }
    }
}