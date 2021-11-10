package com.example.a491bproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class CreateRecipeIngredients : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_recipe_ingredients)

        //Navigate to Steps for add recipe
        val doneBtn: Button = findViewById<Button>(R.id.doneBtn)
        doneBtn.setOnClickListener{
            val createRecipeStepsIntent = Intent(this, CreateRecipeSteps::class.java)
            startActivity(createRecipeStepsIntent)
        }
    }
}