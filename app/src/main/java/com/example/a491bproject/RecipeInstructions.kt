package com.example.a491bproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.example.a491bproject.api.ApiInterface
import com.example.a491bproject.models.Instructions
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RecipeInstructions : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_instructions)

        // Bundle to receive the recipe id from the menu activity
        val bundle = intent.extras
        val recipeId = bundle?.get("recipeId") as Int

        getInstructions(recipeId)
    }

    private fun getInstructions(input: Int) {
        val url = "https://api.spoonacular.com/recipes/$input/"
        val key = "74e154cbd9f64883b37d580e8f04a74f"

        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(url)
            .build()
            .create(ApiInterface::class.java)

        val retrofitData = retrofitBuilder.getRecipeInstructions(key)

        retrofitData.enqueue(object : Callback<ArrayList<Instructions.InstructionsItem>> {
            override fun onResponse(
                call: Call<ArrayList<Instructions.InstructionsItem>>,
                response: Response<ArrayList<Instructions.InstructionsItem>>
            ) {
                val responseBody = response.body()!!

                //TODO Finish Recycler View for instructions
                val myStringBuilder = StringBuilder()

                for (instructions in responseBody) {
                    myStringBuilder.append("Instruction " + instructions.steps[0].step)
                }

                val textView = findViewById<TextView>(R.id.textView10)
                textView.text = myStringBuilder
            }

            override fun onFailure(
                call: Call<ArrayList<Instructions.InstructionsItem>>, t: Throwable) {
                Log.d("MainActivity", "onFailure: $t")
            }
        })
    }
}