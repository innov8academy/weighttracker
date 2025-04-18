package com.example.weighttracker.data.local

import androidx.room.Database
import androidx.room.TypeConverters
import androidx.room.RoomDatabase

@Database(entities = [WeightEntry::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun weightEntryDao(): WeightEntryDao
}