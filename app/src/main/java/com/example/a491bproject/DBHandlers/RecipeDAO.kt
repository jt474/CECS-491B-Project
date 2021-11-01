package com.example.a491bproject.DBHandlers

import com.example.a491bproject.models.RecipeModel

interface RecipeDAO {
    fun insertRecipe(model: RecipeModel): Int
    fun getRecipe(id:String): RecipeModel
    fun updateRecipe(id:String): Int
    fun deleteRecipe(id:String): Int


}