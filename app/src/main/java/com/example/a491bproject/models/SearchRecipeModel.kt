package com.example.a491bproject.models

data class SearchRecipeModel(val model: RecipeModel, val missedIngredientCount: Int, val usedIngredientCount: Int,
                             val missedIngredients: List<String>, val usedIngredients: List<String>,
                             val unusedIngredients: List<String>)
{

}
