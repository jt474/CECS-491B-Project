package com.example.a491bproject.DBHandlers

import com.example.a491bproject.models.RecipeModel
import com.example.a491bproject.models.SearchRecipeModel

interface RecipeSearchDAO {
    fun searchbyID(): RecipeModel
    fun searchbyIngredients(): List<SearchRecipeModel>
}