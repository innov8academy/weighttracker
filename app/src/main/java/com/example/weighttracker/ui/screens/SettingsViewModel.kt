package com.example.weighttracker.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weighttracker.data.repository.WeightRepository
import com.example.weighttracker.data.repository.GoalPrefs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(private val repo: WeightRepository): ViewModel() {
    val prefs: StateFlow<GoalPrefs> = repo.goalPrefs()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), GoalPrefs("Maintain",0f,0f,"kg"))
    fun switchUnit(unit: String) { viewModelScope.launch { repo.switchUnit(unit) } }
}