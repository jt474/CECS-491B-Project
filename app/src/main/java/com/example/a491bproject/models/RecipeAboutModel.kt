package com.example.a491bproject.models

data class RecipeAboutModel(val dateUpdated: String, val description: String){
    constructor() : this("Default", "Default")
    override fun toString(): String {
        return "Last Updated: $dateUpdated | Description: $description\n"
    }
}
