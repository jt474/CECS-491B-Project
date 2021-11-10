package com.example.a491bproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.a491bproject.Adapters.RecipeByIngredientsAdapter
import com.example.a491bproject.api.ApiInterface
import com.example.a491bproject.models.RecipeByIngredients
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class SearchRecipeByIngredient : AppCompatActivity() {

    lateinit var recipesListAdapter: RecipeByIngredientsAdapter
    lateinit var linearLayoutManager: LinearLayoutManager
    lateinit var recyclerViewRecipes: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_recipe_by_ingredient)

        val search = findViewById<Button>(R.id.btn_ingredient_search)
        val userInput = findViewById<EditText>(R.id.et_recipe_ingredient_input)
        recyclerViewRecipes = findViewById<RecyclerView>(R.id.recycler_view_recipes_ingredients)
        recyclerViewRecipes.setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(this)
        recyclerViewRecipes.layoutManager = linearLayoutManager
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

        val retrofitData = retrofitBuilder.searchRecipesByIngredients(input, 20, key)

        retrofitData.enqueue(object : Callback<ArrayList<RecipeByIngredients.RecipeByIngredientsItem>> {
            override fun onResponse(
                call: Call<ArrayList<RecipeByIngredients.RecipeByIngredientsItem>>,
                response: Response<ArrayList<RecipeByIngredients.RecipeByIngredientsItem>>
            ) {
                val responseBody = response.body()!!
                recipesListAdapter = RecipeByIngredientsAdapter(baseContext, responseBody)
                recipesListAdapter.notifyDataSetChanged()
                recyclerViewRecipes.adapter = recipesListAdapter
            }

            override fun onFailure(call: Call<ArrayList<RecipeByIngredients.RecipeByIngredientsItem>>, t: Throwable) {
                Log.d("MainActivity", "onFailure: $t")
            }
        })
    }
}