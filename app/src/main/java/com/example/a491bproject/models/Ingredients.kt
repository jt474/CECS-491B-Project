package com.example.a491bproject.models

data class Ingredients(
    val number: Int,
    val offset: Int,
    val results: List<Result>,
    val totalResults: Int
) {
    data class Result(
        val id: Int,
        val image: String,
        val name: String
    )
}