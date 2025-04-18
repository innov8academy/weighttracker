package com.example.weighttracker.data.preferences

import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

object GoalPreferencesKeys {
    val CURRENT_WEIGHT = floatPreferencesKey("current_weight")
    val GOAL_WEIGHT = floatPreferencesKey("goal_weight")
    val GOAL_TYPE = stringPreferencesKey("goal_type") // "Cut" "Bulk" "Maintain"
    val UNIT = stringPreferencesKey("unit")           // "kg" or "lb"
}