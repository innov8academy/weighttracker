package com.example.weighttracker.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun DailyInputScreen(vm: DailyInputViewModel, onSaved: () -> Unit) {
    var weight by remember { mutableStateOf(0f) }
    var mood by remember { mutableStateOf("") }
    Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        OutlinedTextField(
            value = if (weight == 0f) "" else weight.toString(),
            onValueChange = { weight = it.toFloatOrNull() ?: 0f },
            label = { Text("Today's Weight (kg)") }
        )
        OutlinedTextField(
            value = mood,
            onValueChange = { mood = it },
            label = { Text("Mood (optional)") }
        )
        Button(onClick = {
            vm.save(weight, if (mood.isBlank()) null else mood)
            onSaved()
        }) {
            Text("Save")
        }
    }
}