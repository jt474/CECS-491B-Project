package com.example.a491bproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.a491bproject.Adapters.IngredientAdapter
import com.example.a491bproject.api.ApiInterface
import com.example.a491bproject.models.IngredientInfo
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class IngredientsInformation : AppCompatActivity() {

    lateinit var ingredientsListAdapter: IngredientAdapter
    lateinit var ingredientsLinearLayoutManager: LinearLayoutManager
    lateinit var ingredientsRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ingredients_information)

        val search = findViewById<Button>(R.id.search)
        val userInput = findViewById<EditText>(R.id.userInput)

        ingredientsRecyclerView = findViewById(R.id.recycler_view_ingredients)
        ingredientsRecyclerView.setHasFixedSize(true)
        ingredientsLinearLayoutManager = LinearLayoutManager(this)
        ingredientsRecyclerView.layoutManager = ingredientsLinearLayoutManager

        search.setOnClickListener() {
            val input = userInput.text.toString()
            getMyIngredientInfo(input)
        }

    }

    private fun getMyIngredientInfo(input: String) {
        val url = "https://api.spoonacular.com/food/ingredients/$input/"
        val key = "74e154cbd9f64883b37d580e8f04a74f"

        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(url)
            .build()
            .create(ApiInterface::class.java)

        val retrofitData = retrofitBuilder.getIngredientInfo(1, key)

        retrofitData.enqueue(object : Callback<IngredientInfo> {
            override fun onResponse(
                call: Call<IngredientInfo>,
                response: Response<IngredientInfo>
            ) {
                val responseBody = response.body()!!
                ingredientsListAdapter = IngredientAdapter(baseContext, responseBody)
                ingredientsListAdapter.notifyDataSetChanged()
                ingredientsRecyclerView.adapter = ingredientsListAdapter

//                val myStringBuilder = StringBuilder()
//                val myStringBuilder1 = StringBuilder()
//                val myStringBuilder2 = StringBuilder()
//
//                for (ingredientInfo in responseBody.nutrition.nutrients) {
//                    myStringBuilder.append("Name: " + ingredientInfo.name)
//                    myStringBuilder.append("\n")
//                    myStringBuilder.append("Amount: " + ingredientInfo.amount)
//                    myStringBuilder.append("\n")
//                    myStringBuilder.append("Unit: " + ingredientInfo.unit)
//                    myStringBuilder.append("\n")
//                    myStringBuilder.append("Daily Needs: " + ingredientInfo.percentOfDailyNeeds)
//                    myStringBuilder.append("\n")
//                }
//
//                val textView2 = findViewById<TextView>(R.id.tv_ingredient)
//                Log.i("MainActivity", "API call: $myStringBuilder")
//                textView2.text = myStringBuilder
//                textView2.movementMethod = ScrollingMovementMethod()
//
//                for (ingredientInfo in responseBody.nutrition.properties) {
//                    myStringBuilder1.append("Name: " + ingredientInfo.name)
//                    myStringBuilder1.append("\n")
//                    myStringBuilder1.append("Amount: " + ingredientInfo.amount)
//                    myStringBuilder1.append("\n")
//                    myStringBuilder1.append("Unit: " + ingredientInfo.unit)
//                    myStringBuilder1.append("\n")
//                }
//
//                val textView3 = findViewById<TextView>(R.id.tv_ingredient1)
//                textView3.text = myStringBuilder1
//                textView3.movementMethod = ScrollingMovementMethod()
//
//                myStringBuilder2.append("Protein: " + responseBody.nutrition.caloricBreakdown.percentProtein)
//                myStringBuilder2.append("\n")
//                myStringBuilder2.append("Fat: " + responseBody.nutrition.caloricBreakdown.percentFat)
//                myStringBuilder2.append("\n")
//                myStringBuilder2.append("Carbs: " + responseBody.nutrition.caloricBreakdown.percentCarbs)
//                myStringBuilder2.append("\n")
//
//
//                val textView4 = findViewById<TextView>(R.id.tv_ingredient2)
//                textView4.text = myStringBuilder2
//                textView4.movementMethod = ScrollingMovementMethod()
            }

            override fun onFailure(call: Call<IngredientInfo>, t: Throwable) {
                Log.d("MainActivity", "onFailure: $t")
            }
        })
    }
}