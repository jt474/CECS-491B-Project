package com.example.a491bproject.models

class IngredientModel(val name: String, val amount: String, val unit: String) {
    constructor() : this("name", "0", "unit")

    override fun toString(): String {
        return "$name $amount $unit"
    }

}