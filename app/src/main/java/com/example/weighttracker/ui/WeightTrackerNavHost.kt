package com.example.weighttracker.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.weighttracker.ui.navigation.Routes
import com.example.weighttracker.ui.screens.*

@Composable
fun WeightTrackerNavHost(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Routes.Onboarding,
        modifier = modifier
    ) {
        composable(Routes.Onboarding) {
            val vm: OnboardingViewModel = hiltViewModel()
            OnboardingScreen(vm) { navController.navigate(Routes.Dashboard) }
        }
        composable(Routes.DailyInput) {
            val vm: DailyInputViewModel = hiltViewModel()
            DailyInputScreen(vm) { navController.popBackStack() }
        }
        composable(Routes.Dashboard) {
            val vm: DashboardViewModel = hiltViewModel()
            DashboardScreen(vm, onAdd = { navController.navigate(Routes.DailyInput) }, onHistory = { navController.navigate(Routes.History) }, onSettings = { navController.navigate(Routes.Settings) })
        }
        composable(Routes.History) {
            val vm: HistoryViewModel = hiltViewModel()
            HistoryScreen(vm)
        }
        composable(Routes.Settings) {
            val vm: SettingsViewModel = hiltViewModel()
            SettingsScreen(vm)
        }
    }
}