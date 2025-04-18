package com.example.weighttracker.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weighttracker.data.repository.WeightRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(private val repo: WeightRepository) : ViewModel() {
    fun saveGoal(goalType: String, current: Float, goal: Float) {
        viewModelScope.launch { repo.saveGoal(goalType, current, goal) }
    }
}