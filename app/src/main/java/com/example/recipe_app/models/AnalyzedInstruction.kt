package com.example.recipe_app.models

data class AnalyzedInstruction(
    val name: String,
    val steps: List<Step>
)