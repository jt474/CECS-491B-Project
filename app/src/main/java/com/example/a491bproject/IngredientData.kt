package com.example.a491bproject

import android.util.Log
import com.example.a491bproject.api.ApiInterface
import com.example.a491bproject.models.IngredientInfo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

data class IngredientData(val ingredientID: String) {
    val id = ingredientID

    fun getMyIngredientInfo(): String {
        val url = "https://api.spoonacular.com/food/ingredients/$id/"

        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(url)
            .build()
            .create(ApiInterface::class.java)

        val retrofitData = retrofitBuilder.getIngredientInfo()
        val myStringBuilder = StringBuilder()

        retrofitData.enqueue(object : Callback<IngredientInfo> {
            override fun onResponse(
                call: Call<IngredientInfo>,
                response: Response<IngredientInfo>
            ) {
                val responseBody = response.body()!!


                for (ingredientInfo in responseBody.nutrition.nutrients) {
                    myStringBuilder.append(ingredientInfo)
                    myStringBuilder.append("\n")
                }
            }

            override fun onFailure(call: Call<IngredientInfo>, t: Throwable) {
                Log.d("MainActivity", "onFailure: $t")
            }
        })

        return myStringBuilder.toString()
    }
}
