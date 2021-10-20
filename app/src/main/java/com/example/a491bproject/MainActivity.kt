package com.example.a491bproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import com.example.a491bproject.api.ApiInterface
import com.example.a491bproject.models.IngredientInfo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://api.spoonacular.com/"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getMyIngredientInfo()

        //Transitions from Main to Ingredients
        val ingredientsListBtn: Button = findViewById<Button>(R.id.ingredientsListBtn)
        ingredientsListBtn.setOnClickListener() {
            val ingredientsIntent = Intent(this, IngredientsActivity::class.java)
            startActivity(ingredientsIntent)

        }

        // Naming for menu
        val actionBar = supportActionBar

        if(actionBar != null){
            actionBar.title = "Main Menu"
        }


    }

    private fun getMyIngredientInfo() {
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ApiInterface::class.java)

        val retrofitData = retrofitBuilder.getIngredientInfo()

        retrofitData.enqueue(object : Callback<List<IngredientInfo>?> {
            override fun onResponse(
                call: Call<List<IngredientInfo>?>,
                response: Response<List<IngredientInfo>?>
            ) {
                val responseBody = response.body()!!

                val myStringBuilder = StringBuilder()

                for (ingredientInfo in responseBody) {
                    myStringBuilder.append(ingredientInfo.nutrition.nutrients)
                    myStringBuilder.append("\n")
                }

                val textView2 = findViewById<TextView>(R.id.textView2)
                textView2.text = myStringBuilder
            }

            override fun onFailure(call: Call<List<IngredientInfo>?>, t: Throwable) {

            }
        })
    }
}