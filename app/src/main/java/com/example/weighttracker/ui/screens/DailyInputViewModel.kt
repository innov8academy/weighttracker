package com.example.weighttracker.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weighttracker.data.repository.WeightRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DailyInputViewModel @Inject constructor(private val repo: WeightRepository) : ViewModel() {
    fun save(weight: Float, mood: String?) {
        viewModelScope.launch { repo.addEntry(weight, mood) }
    }
}