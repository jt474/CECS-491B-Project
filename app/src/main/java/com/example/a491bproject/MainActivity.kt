package com.example.a491bproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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


        // Spoontacular call
        var rf = Retrofit.Builder()
            .baseUrl(RetrofitInterface.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()

        var API = rf.create(RetrofitInterface::class.java)
        var call = API.info

        call?.enqueue(object: Callback<List<IngredientInfo?>?> {
            override fun onResponse(
                call: Call<List<IngredientInfo?>?>,
                response: Response<List<IngredientInfo?>?>
            ) {
                var infolist : List<IngredientInfo>? = response.body() as List<IngredientInfo>
                var info = arrayOfNulls<String>(infolist!!.size)

                for (i in infolist!!.indices)
                    info[i] = infolist!![i]!!.original

                var adapter = ArrayAdapter<String>(applicationContext, android.R.layout.simple_dropdown_item_1line, info)
                findViewById<ListView>(R.id.listview)?.adapter = adapter

            }

            override fun onFailure(call: Call<List<IngredientInfo?>?>, t: Throwable) {
            }

        })

    }
}