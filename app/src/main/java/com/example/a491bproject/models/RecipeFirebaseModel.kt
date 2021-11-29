package com.example.a491bproject.models

data class RecipeFirebaseModel(val authorID: String, val title: String, val ingredients: List<IngredientModel>, val instructions: List<InstructionModel>, val id: String = ""){
    //id is the unique identifier of a recipe
    //title is the title of a recipe.
    //ingredients is a list of ingredient names
    //instructions is a list of a data class InstructionModels

    //TODO: Discuss the important of nutritional information of user uploaded recipes.
    // We would need to acquire data, and combine it as an additional feature.
}
