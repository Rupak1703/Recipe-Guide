package com.example.recipe_app.models

data class WinePairing(
    val pairedWines: List<String>,
    val pairingText: String,
    val productMatches: List<ProductMatche>
)