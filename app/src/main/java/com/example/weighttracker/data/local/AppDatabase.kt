package com.example.weighttracker.data.local

import androidx.room.Database
import androidx.room.TypeConverters
import androidx.room.RoomDatabase

@Database(
    entities = [WeightEntry::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun weightDao(): WeightDao
}