package com.example.weighttracker.util

object MotivationalMessages {
    private val cutMessages = listOf("Keep shredding, you're a sculptor of your own body! 🏆", "Every gram lost is a victory! 💪")
    private val bulkMessages = listOf("Fuel the gains! 🏋️‍♂️", "Every bite builds the beast. 🍗")
    private val maintainMessages = listOf("Balance is power. ⚖️", "Consistency is king. 👑")
    fun randomMessage(goal: String) = when(goal) {
        "Cut" -> cutMessages.random()
        "Bulk" -> bulkMessages.random()
        else -> maintainMessages.random()
    }
}