package com.example.a491bproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.a491bproject.api.ApiInterface
import com.example.a491bproject.models.IngredientInfo
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class IngredientsInformation : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ingredients_information)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        val search = findViewById<Button>(R.id.search)
        val userInput = findViewById<EditText>(R.id.userInput)
        search.setOnClickListener() {
            val input = userInput.text.toString()
            getMyIngredientInfo(input)
        }

    }

    private fun getMyIngredientInfo(input: String) {
        val url = "https://api.spoonacular.com/food/ingredients/$input/"

        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(url)
            .build()
            .create(ApiInterface::class.java)

        val retrofitData = retrofitBuilder.getIngredientInfo()

        retrofitData.enqueue(object : Callback<IngredientInfo> {
            override fun onResponse(
                call: Call<IngredientInfo>,
                response: Response<IngredientInfo>
            ) {
                val responseBody = response.body()!!

                val myStringBuilder = StringBuilder()


                for (ingredientInfo in responseBody.nutrition.nutrients) {
                    myStringBuilder.append(ingredientInfo)
                    myStringBuilder.append("\n")
                }

                val textView2 = findViewById<TextView>(R.id.tv_ingredient)
                Log.i("MainActivity", "API call: $myStringBuilder")
                textView2.text = myStringBuilder
            }

            override fun onFailure(call: Call<IngredientInfo>, t: Throwable) {
                Log.d("MainActivity", "onFailure: $t")
            }
        })
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}
