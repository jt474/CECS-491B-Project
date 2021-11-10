package com.example.a491bproject.models

data class Recipes(
    val number: Int,
    val offset: Int,
    val results: List<Recipe>,
    val totalResults: Int
)