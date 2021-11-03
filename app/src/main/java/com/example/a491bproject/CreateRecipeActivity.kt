package com.example.a491bproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class CreateRecipeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_recipe)

        //Navigate to add Ingredients for recipe
        val createBtn: Button = findViewById<Button>(R.id.create)
        createBtn.setOnClickListener{
            val createIngredientsIntent = Intent(this, CreateRecipeIngredients::class.java)
            startActivity(createIngredientsIntent)
        }
    }
}