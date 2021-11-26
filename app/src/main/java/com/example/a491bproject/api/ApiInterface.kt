package com.example.a491bproject.api

import com.example.a491bproject.models.IngredientInfo
import com.example.a491bproject.models.Instructions
import com.example.a491bproject.models.RecipeByIngredients
import com.example.a491bproject.models.Recipes
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("information")
    fun getIngredientInfo(@Query("amount") amount: Int, @Query("apiKey") apiKey: String): Call<IngredientInfo>

    @GET("complexSearch")
    fun searchRecipes(@Query("query") query: String, @Query("number") number: Int, @Query("apiKey") apiKey: String): Call<Recipes>

    @GET("findByIngredients")
    fun searchRecipesByIngredients(@Query("ingredients") ingredients: String, @Query("number") number: Int, @Query("apiKey") apiKey: String): Call<ArrayList<RecipeByIngredients.RecipeByIngredientsItem>>

    @GET("analyzedInstructions")
    fun getRecipeInstructions(@Query("apiKey") apiKey: String): Call<ArrayList<Instructions.InstructionsItem>>
}