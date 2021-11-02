package com.example.a491bproject.models

data class Recipes(
    val number: Int,
    val offset: Int,
    val results: List<Result>,
    val totalResults: Int
) {
    data class Result(
        val calories: Int,
        val carbs: String,
        val fat: String,
        val id: Int,
        val image: String,
        val imageType: String,
        val protein: String,
        val title: String
    )
}