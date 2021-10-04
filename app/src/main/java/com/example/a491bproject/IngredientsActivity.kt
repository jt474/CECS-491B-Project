package com.example.a491bproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class IngredientsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ingredients)

        //Name of Activity on top left
        val actionBar = supportActionBar

        if(actionBar != null){
            actionBar.title = "Ingredients Activity"
        }
    }
}