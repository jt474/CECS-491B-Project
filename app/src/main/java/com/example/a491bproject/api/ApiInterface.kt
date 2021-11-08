package com.example.a491bproject.api

import com.example.a491bproject.models.IngredientInfo
import com.example.a491bproject.models.Recipes
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("information?amount=1&apiKey=74e154cbd9f64883b37d580e8f04a74f")
    fun getIngredientInfo(): Call<IngredientInfo>

    @GET("findByNutrients?minCarbs=10&maxCarbs=50")
    fun getRecipe(@Query("number") number: Int, @Query("apiKey") apiKey: String): Call<List<Recipes>>

}