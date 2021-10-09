package com.example.a491bproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast


class AddIngredientsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val ingredient: TextView = findViewById(R.id.editTextIngredient)
        val quantity: TextView = findViewById(R.id.editTextQuantity)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ingredients)


        //Naming for menu
        val actionBar = supportActionBar
        if(actionBar != null) {
            actionBar.title = "Ingredients Activity"
        }


        title = "KotlinApp"
        val context = this
        val db = DataBaseHandler(context)
        val button = findViewById<TextView>(R.id.buttonAddIngredient)
        button.setOnClickListener {
            if (ingredient.text.toString().isNotEmpty() &&
                quantity.text.toString().isNotEmpty()
            ) {
                val user = User(ingredient.text.toString(), quantity.text.toString().toDouble())
                db.insertData(user)
                clearField()
            }
            else {
                Toast.makeText(context, "Input Ingredient and Quantity", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun clearField() {
        findViewById<EditText>(R.id.editTextIngredient).setText("")
        findViewById<EditText>(R.id.editTextQuantity).setText("")
    }

    class User(ingredientInput: String, quantityInput: Double){
        val ingredient = ingredientInput
        val quantity = quantityInput
    }
}