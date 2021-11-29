package com.example.a491bproject.models

class Instructions : ArrayList<Instructions.InstructionsItem>() {
    data class InstructionsItem(
        val name: String,
        val steps: List<Step>
    ) {
        data class Step(
            val equipment: List<Equipment>,
            val ingredients: List<Ingredient>,
            val length: Length,
            val number: Int,
            val step: String
        ) {
            data class Equipment(
                val id: Int,
                val image: String,
                val localizedName: String,
                val name: String
            )

            data class Ingredient(
                val id: Int,
                val image: String,
                val localizedName: String,
                val name: String
            )

            data class Length(
                val number: Int,
                val unit: String
            )
        }
    }
}