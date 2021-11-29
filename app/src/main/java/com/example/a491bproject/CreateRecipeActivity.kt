package com.example.a491bproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.a491bproject.models.IngredientModel
import com.example.a491bproject.models.InstructionModel
import com.example.a491bproject.models.RecipeAboutModel

class CreateRecipeActivity : AppCompatActivity() {
    private lateinit var ingredients: MutableList<IngredientModel>
    private lateinit var instructions: MutableList<InstructionModel>
    private lateinit var about: RecipeAboutModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_recipe)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        //Navigate to add Ingredients for recipe
        val createBtn: Button = findViewById<Button>(R.id.create)
        createBtn.setOnClickListener{
            val createIngredientsIntent = Intent(this, CreateRecipeIngredients::class.java)
            startActivity(createIngredientsIntent)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}