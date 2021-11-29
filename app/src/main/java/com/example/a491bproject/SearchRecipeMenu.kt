package com.example.a491bproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.a491bproject.adapters.RecipeByIngredientsAdapter
import com.example.a491bproject.api.ApiInterface
import com.example.a491bproject.models.Recipes
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SearchRecipeMenu : AppCompatActivity() {

    lateinit var recipesByIngredientsListAdapter: RecipeByIngredientsAdapter
    lateinit var recipeLinearLayoutManager: LinearLayoutManager
    lateinit var recipeRecyclerViewRecipes: RecyclerView
    lateinit var recipesListAdapter: RecipesListAdapter
    lateinit var linearLayoutManager: LinearLayoutManager
    lateinit var recyclerViewRecipes: RecyclerView
    lateinit var radioGroup: RadioGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_recipe_menu)

        val search = findViewById<Button>(R.id.btn_recipe_search)
        val userInput = findViewById<EditText>(R.id.et_recipe_input)
        val searchOptions = findViewById<RadioGroup>(R.id.rg_search_recipes_options)
        recyclerViewRecipes = findViewById<RecyclerView>(R.id.recycler_view_recipes)
        recyclerViewRecipes.setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(this)
        recyclerViewRecipes.layoutManager = linearLayoutManager
        radioGroup = findViewById(R.id.rg_different_search)

//        recipeRecyclerViewRecipes = findViewById(R.id.recycler_view_recipes_ingredients)
//        recipeRecyclerViewRecipes.setHasFixedSize(true)
//        recipeLinearLayoutManager = LinearLayoutManager(this)
//        recipeRecyclerViewRecipes.layoutManager = recipeLinearLayoutManager

        search.setOnClickListener() {
            val input = userInput.text.toString()
            getRecipes(input)
//            val checkId = radioGroup.checkedRadioButtonId
//            if (checkId == R.id.rb_ingredient_search) {
//                getRecipes(input)
//            } else {
//                getRecipesByIngredients(input)
//            }
        }

        //Navigate to Search By Ingredient
        val searchRecipeBtn: Button = findViewById<Button>(R.id.btn_recipe_ingredient_search)
        searchRecipeBtn.setOnClickListener() {
            val searchRecipeIntent = Intent(this, SearchRecipeByIngredient::class.java)
            startActivity(searchRecipeIntent)

        }
        // the hint "clam chowder" should be the default hint to prompt user to enter recipe name
        onRadioButtonClicked(searchOptions.findViewById(R.id.rb_search_recipe_by_name))
    }

    fun onRadioButtonClicked(view: View) {
        val userInput = findViewById<EditText>(R.id.et_recipe_input)
        if (view is RadioButton) {
            // Is the button now checked?
            val checked = view.isChecked
            // Check which radio button was clicked
            when (view.getId()) {
                R.id.rb_search_recipe_by_name ->
                    if (checked) {
                        userInput.setHint("clam chowder")
                    }
                R.id.rb_search_recipe_by_ingredients ->
                    if (checked) {
                        userInput.setHint("salmon, rice")
                    }
            }
        }
    }


    private fun getRecipes(input: String) {
        val url = "https://api.spoonacular.com/recipes/"
        val key = "7f3ed0e0a5844986b862d89b0e2481fc"

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


//    private fun getRecipesByIngredients(input: String) {
//        val url = "https://api.spoonacular.com/recipes/"
//        val key = "74e154cbd9f64883b37d580e8f04a74f"
//
//        val retrofitBuilder = Retrofit.Builder()
//            .addConverterFactory(GsonConverterFactory.create())
//            .baseUrl(url)
//            .build()
//            .create(ApiInterface::class.java)
//
//        val retrofitData = retrofitBuilder.searchRecipesByIngredients(input, 20, key)
//
//        retrofitData.enqueue(object : Callback<ArrayList<RecipeByIngredients.RecipeByIngredientsItem>> {
//            override fun onResponse(
//                call: Call<ArrayList<RecipeByIngredients.RecipeByIngredientsItem>>,
//                response: Response<ArrayList<RecipeByIngredients.RecipeByIngredientsItem>>
//            ) {
//                val responseBody = response.body()!!
//                recipesByIngredientsListAdapter = RecipeByIngredientsAdapter(baseContext, responseBody)
//                recipesByIngredientsListAdapter.notifyDataSetChanged()
//                recipeRecyclerViewRecipes.adapter = recipesByIngredientsListAdapter
//            }
//
//            override fun onFailure(call: Call<ArrayList<RecipeByIngredients.RecipeByIngredientsItem>>, t: Throwable) {
//                Log.d("MainActivity", "onFailure: $t")
//            }
//        })
//    }
}