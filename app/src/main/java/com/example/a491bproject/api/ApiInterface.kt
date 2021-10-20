package com.example.a491bproject.api

import com.example.a491bproject.models.IngredientInfo
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("food/ingredients/9266/information?amount=1&apiKey=74e154cbd9f64883b37d580e8f04a74f")
    fun getIngredientInfo(): Call<IngredientInfo>
}