package com.example.weighttracker.ui.previews

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.weighttracker.ui.screens.OnboardingScreen
import com.example.weighttracker.ui.theme.WeightTrackerTheme

@Preview(showBackground = true)
@Composable
fun OnboardingPreview() {
    WeightTrackerTheme { OnboardingScreen(vm = object : com.example.weighttracker.ui.screens.OnboardingViewModel(null!!) {}, onDone = {}) }
}