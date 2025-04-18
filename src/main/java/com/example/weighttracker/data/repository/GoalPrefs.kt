package com.example.weighttracker.data.repository

data class GoalPrefs(
    val goalType: String,
    val current: Float,
    val goal: Float,
    val unit: String
)