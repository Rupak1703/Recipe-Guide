package com.example.recipe_app.models

data class ExtendedIngredientX(
    val aisle: String,
    val amount: Double,
    val consitency: String,
    val id: Int,
    val image: String,
    val measures: MeasuresX,
    val meta: List<String>,
    val name: String,
    val original: String,
    val originalName: String,
    val unit: String
)