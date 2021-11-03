package com.example.a491bproject.DBHandlers

import com.example.a491bproject.models.RecipeFirebaseModel
import com.example.a491bproject.models.SearchRecipeModel

interface RecipeSearchDAO {
    fun searchbyID(): RecipeFirebaseModel
    fun searchbyIngredients(): List<SearchRecipeModel>
}