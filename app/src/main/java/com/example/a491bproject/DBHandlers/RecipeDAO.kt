package com.example.a491bproject.DBHandlers

import com.example.a491bproject.models.RecipeFirebaseModel

interface RecipeDAO {
    fun insertRecipe(firebaseModel: RecipeFirebaseModel): Int
    fun getRecipe(id:String): RecipeFirebaseModel
    fun updateRecipe(id:String): Int
    fun deleteRecipe(id:String): Int


}