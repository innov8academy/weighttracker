package com.example.weighttracker.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.weighttracker.data.local.WeightEntry
import java.time.format.DateTimeFormatter

@Composable
fun HistoryScreen(vm: HistoryViewModel) {
    val entries by vm.entries.collectAsState()
    val formatter = DateTimeFormatter.ofPattern("MMM dd")
    LazyColumn {
        items(entries) { entry ->
            Card(
                Modifier
                    .fillMaxWidth()
                    .padding(4.dp)
            ) {
                Row(
                    Modifier.padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(entry.date.format(formatter), Modifier.weight(1f))
                    Text("${entry.weight} kg", Modifier.weight(1f))
                    entry.mood?.let { Text(it) }
                    IconButton(onClick = { vm.delete(entry) }) {
                        Icon(Icons.Default.Delete, contentDescription = null)
                    }
                }
            }
        }
    }
}