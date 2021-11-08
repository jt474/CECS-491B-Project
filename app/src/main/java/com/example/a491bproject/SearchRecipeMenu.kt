package com.example.a491bproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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

//        I had to comment this out and move it into the getRecipes fuction...
//        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
//        recyclerView.setHasFixedSize(true)
//        recyclerView.layoutManager = LinearLayoutManager(this)

        val search = findViewById<Button>(R.id.searchRecipe)
        val userInput = findViewById<EditText>(R.id.recipeInput)
        search.setOnClickListener() {
            //val input = userInput.text.toString()
            getRecipes()
        }
    }

    private fun getRecipes() {
        val url = "https://api.spoonacular.com/recipes/"
        val key = "74e154cbd9f64883b37d580e8f04a74f"

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(url)
            .build()
            .create(ApiInterface::class.java)

        val retrofitData = retrofitBuilder.getRecipe(2, key)

        retrofitData.enqueue(object : Callback<List<Recipes>> {
            override fun onResponse(
                call: Call<List<Recipes>>,
                response: Response<List<Recipes>>
            ) {
                val responseBody = response.body()!!

                val myAdapter = MyAdapter(baseContext, responseBody)
                recyclerView.adapter = myAdapter

            }

            override fun onFailure(call: Call<List<Recipes>>, t: Throwable) {
                Log.d("MainActivity", "onFailure: $t")
            }
        })
    }
}