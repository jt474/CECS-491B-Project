package com.example.a491bproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.a491bproject.api.ApiInterface
import com.example.a491bproject.models.Recipes
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SearchRecipeMenu : AppCompatActivity() {

    lateinit var recipesListAdapter: RecipesListAdapter
    lateinit var linearLayoutManager: LinearLayoutManager
    lateinit var recyclerViewRecipes: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_recipe_menu)

        val search = findViewById<Button>(R.id.btn_recipe_search)
        val userInput = findViewById<EditText>(R.id.et_recipe_input)
        recyclerViewRecipes = findViewById<RecyclerView>(R.id.recycler_view_recipes)
        recyclerViewRecipes.setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(this)
        recyclerViewRecipes.layoutManager = linearLayoutManager
        search.setOnClickListener() {
            val input = userInput.text.toString()
            getRecipes(input)
        }

        //Navigate to Search By Ingredient
        val searchRecipeBtn: Button = findViewById<Button>(R.id.btn_recipe_ingredient_search)
        searchRecipeBtn.setOnClickListener() {
            val searchRecipeIntent = Intent(this, SearchRecipeByIngredient::class.java)
            startActivity(searchRecipeIntent)

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

        val retrofitData = retrofitBuilder.searchRecipes(input, 20, key)

        retrofitData.enqueue(object : Callback<Recipes> {
            override fun onResponse(
                call: Call<Recipes>,
                response: Response<Recipes>
            ) {
                val responseBody = response.body()!!
                recipesListAdapter = RecipesListAdapter(baseContext, responseBody)
                recipesListAdapter.notifyDataSetChanged()
                recyclerViewRecipes.adapter = recipesListAdapter
            }

            override fun onFailure(call: Call<Recipes>, t: Throwable) {
                Log.d("MainActivity", "onFailure: $t")
            }
        })
    }
}