package com.example.weighttracker.data.repository

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.example.weighttracker.data.local.WeightEntry
import com.example.weighttracker.data.local.WeightEntryDao
import com.example.weighttracker.data.preferences.GoalPreferencesKeys
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import java.io.File
import java.time.format.DateTimeFormatter
import javax.inject.Singleton

@Singleton
class WeightRepository(
    private val dao: WeightEntryDao,
    private val dataStore: DataStore<Preferences>
) {
    fun getEntries(): Flow<List<WeightEntry>> = dao.getAll()
    fun getLatest(): Flow<WeightEntry?> = dao.latest()
    suspend fun addEntry(weight: Float, mood: String?) =
        dao.insert(WeightEntry(weight = weight, mood = mood))

    suspend fun updateEntry(entry: WeightEntry) = dao.update(entry)
    suspend fun deleteEntry(entry: WeightEntry) = dao.delete(entry)

    fun goalPrefs(): Flow<GoalPrefs> = dataStore.data.map { prefs ->
        GoalPrefs(
            goalType = prefs[GoalPreferencesKeys.GOAL_TYPE] ?: "Maintain",
            current = prefs[GoalPreferencesKeys.CURRENT_WEIGHT] ?: 0f,
            goal = prefs[GoalPreferencesKeys.GOAL_WEIGHT] ?: 0f,
            unit = prefs[GoalPreferencesKeys.UNIT] ?: "kg"
        )
    }

    suspend fun saveGoal(goalType: String, current: Float, goal: Float) {
        dataStore.edit { prefs ->
            prefs[GoalPreferencesKeys.GOAL_TYPE] = goalType
            prefs[GoalPreferencesKeys.CURRENT_WEIGHT] = current
            prefs[GoalPreferencesKeys.GOAL_WEIGHT] = goal
        }
    }

    suspend fun switchUnit(unit: String) {
        dataStore.edit { it[GoalPreferencesKeys.UNIT] = unit }
    }

    suspend fun exportCsv(context: Context): File {
        val file = File(context.cacheDir, "weights_${System.currentTimeMillis()}.csv")
        val entries = getEntries().first()
        file.printWriter().use { out ->
            out.println("date,weight,mood")
            val formatter = DateTimeFormatter.ISO_LOCAL_DATE
            entries.forEach {
                out.println("${it.date.format(formatter)},${it.weight},${it.mood ?: ""}")
            }
        }
        return file
    }
}

data class GoalPrefs(
    val goalType: String,
    val current: Float,
    val goal: Float,
    val unit: String
)