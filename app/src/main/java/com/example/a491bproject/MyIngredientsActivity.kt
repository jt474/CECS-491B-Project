package com.example.a491bproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MyIngredientsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_ingredients)
        back()
    }

    private fun back() {
        findViewById<Button>(R.id.button_back).setOnClickListener { this.finish() }
    }
}

object MyIngredients {
    private val ingredients = mutableListOf<Ingredient>()

    fun addIngredient(ingredient: Ingredient) {
        ingredients.add(ingredient)
    }

    init {
        ingredients.add(Ingredient("pineapple"))
        ingredients.add(Ingredient("apple", "5"))
        ingredients.add(Ingredient("banana"))
        ingredients.add(Ingredient("egg", "10"))
        ingredients.add(Ingredient("lettuce"))
        ingredients.add(Ingredient("rib eye", "20", "oz"))
    }
}