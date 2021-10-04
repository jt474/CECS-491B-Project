package com.example.a491bproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val ingredientsListBtn: Button = findViewById<Button>(R.id.ingredientsListBtn)
        ingredientsListBtn.setOnClickListener() {
            val ingredientsIntent = Intent(this, IngredientsActivity::class.java)
            startActivity(ingredientsIntent)

        }
        val actionBar = supportActionBar

        if(actionBar != null){
            actionBar.title = "Main Menu"
        }
    }
}