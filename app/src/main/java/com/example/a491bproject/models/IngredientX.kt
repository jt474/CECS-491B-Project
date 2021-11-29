package com.example.a491bproject.models

data class IngredientX(
    val amount: Double,
    val id: Int,
    val name: String,
    val nutrients: List<Nutrient>,
    val unit: String
)