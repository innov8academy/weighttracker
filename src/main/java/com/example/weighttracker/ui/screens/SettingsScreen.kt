package com.example.weighttracker.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SettingsScreen(vm: SettingsViewModel) {
    val prefs by vm.prefs.collectAsState()
    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Text("Units", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Row {
            RadioButton(selected = prefs.unit == "kg", onClick = { vm.switchUnit("kg") })
            Text("kg")
            Spacer(Modifier.width(16.dp))
            RadioButton(selected = prefs.unit == "lb", onClick = { vm.switchUnit("lb") })
            Text("lb")
        }
        // Other settings: reset, export CSV, etc.
    }
}