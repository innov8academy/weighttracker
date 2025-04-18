package com.example.weighttracker.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "weight_entries")
data class WeightEntry(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val date: LocalDate = LocalDate.now(),
    val weight: Float,
    val mood: String? = null
)