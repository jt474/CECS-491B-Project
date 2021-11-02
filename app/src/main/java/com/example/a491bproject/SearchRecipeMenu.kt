package com.example.a491bproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.a491bproject.api.ApiInterface
import com.example.a491bproject.models.IngredientInfo
import com.example.a491bproject.models.Recipes
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SearchRecipeMenu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_recipe_menu)

        val search = findViewById<Button>(R.id.searchRecipe)
        val userInput = findViewById<EditText>(R.id.recipeInput)
        search.setOnClickListener() {
            val input = userInput.text.toString()
            getRecipes(input)
        }
    }

    private fun getRecipes(input: String) {
        val url = "https://api.spoonacular.com/recipes/"
        val key = "74e154cbd9f64883b37d580e8f04a74f"

        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(url)
            .build()
            .create(ApiInterface::class.java)

        val retrofitData = retrofitBuilder.getRecipe(input, 2, key)

        retrofitData.enqueue(object : Callback<Recipes> {
            override fun onResponse(
                call: Call<Recipes>,
                response: Response<Recipes>
            ) {
                val responseBody = response.body()!!

                val myStringBuilder = StringBuilder()


                for (recipes in responseBody.results) {
                    myStringBuilder.append(recipes.title)
                    myStringBuilder.append("\n")
                    myStringBuilder.append(recipes.calories)
                    myStringBuilder.append("\n")
                    myStringBuilder.append(recipes.carbs)
                    myStringBuilder.append("\n")
                    myStringBuilder.append(recipes.fat)
                    myStringBuilder.append("\n")
                }

                val textView3 = findViewById<TextView>(R.id.tv_recipes)
                Log.i("MainActivity", "API call: $myStringBuilder")
                textView3.text = myStringBuilder
            }

            override fun onFailure(call: Call<Recipes>, t: Throwable) {
                Log.d("MainActivity", "onFailure: $t")
            }
        })
    }
}