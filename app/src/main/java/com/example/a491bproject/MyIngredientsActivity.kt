package com.example.a491bproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.lang.StringBuilder

class MyIngredientsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_ingredients)
        displayIngredients()
        back()
    }

    private fun back() {
        findViewById<Button>(R.id.button_back).setOnClickListener { this.finish() }
    }

    private fun displayIngredients() {
        val textViewIngredient = findViewById<TextView>(R.id.textView_ingredients)
        val myStringBuilder = StringBuilder()
        for (ingredient in MyIngredients.showIngredients()) {
            myStringBuilder.append("${ingredient.name} Quantity: ${ingredient.quantity} \n")
        }
        textViewIngredient.text = myStringBuilder
    }
}

object MyIngredients {
    private val ingredients = mutableListOf<Ingredient>()

    fun addIngredient(ingredient: Ingredient) {
        ingredients.add(0, ingredient)
    }

    fun showIngredients(): List<Ingredient> {
        return ingredients
    }

    init {
        ingredients.add(Ingredient("pineapple"))
        ingredients.add(Ingredient("apple", "5"))
        ingredients.add(Ingredient("banana"))
        ingredients.add(Ingredient("egg", "10"))
        ingredients.add(Ingredient("lettuce"))
        ingredients.add(Ingredient("rib eye", "20 oz"))
    }
}