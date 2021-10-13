package com.example.a491bproject

import retrofit2.Call
import retrofit2.http.GET

interface RetrofitInterface {

    @get:GET("information?amount=1&apiKey=7f3ed0e0a5844986b862d89b0e2481fc")
    val info : Call<List<IngredientInfo?>?>?

    companion object {
        const val BASE_URL = "https://api.spoonacular.com/food/ingredients/9266/"
    }
}