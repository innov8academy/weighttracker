package com.example.weighttracker.data.local

import androidx.room.TypeConverter;
import java.time.LocalDate;

class Converters {
    // ───── LocalDate <‑‑‑‑> Long (epoch days) ─────
    @TypeConverter
    fun fromLocalDate(date: LocalDate?): Long? =
        date?.toEpochDay()

    @TypeConverter
    fun toLocalDate(epochDays: Long?): LocalDate? =
        epochDays?.let(LocalDate::ofEpochDay)
}