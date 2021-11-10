package com.example.a491bproject.DBHandlers

import com.example.a491bproject.models.RecipeFirebaseModel
import com.example.a491bproject.models.UserRecipesModel

interface RecipeDAO {
    fun insertRecipe(firebaseModel: RecipeFirebaseModel): Int
    fun getRecipe(id:String): RecipeFirebaseModel
    fun getUserRecipes(): MutableList<UserRecipesModel>
    fun updateRecipe(id:String): Int
    fun deleteRecipe(id:String): Int


}