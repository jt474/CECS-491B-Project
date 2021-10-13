package com.example.a491bproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class IngredientsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ingredients)

        //Naming for menu
        val actionBar = supportActionBar

        if (actionBar != null) {
            actionBar.title = "Ingredients Activity"
        }

        val button = findViewById<TextView>(R.id.buttonAddIngredient)
        button.setOnClickListener {
//            val db = DataBaseHandler(this)
            val ingredient: TextView = findViewById(R.id.editTextIngredient)
            val quantity: TextView = findViewById(R.id.editTextQuantity)
            if (ingredient.text.toString().isNotEmpty() &&
                quantity.text.toString().isNotEmpty()
            ) {
                val user = AddIngredientsActivity.User(
                    ingredient.text.toString(),
                    quantity.text.toString().toDouble()
                )
//                db.insertData(user)
                clearField()
            } else {
                Toast.makeText(this, "Input Ingredient and Quantity", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun clearField() {
        findViewById<EditText>(R.id.editTextIngredient).text.clear()
        findViewById<EditText>(R.id.editTextQuantity).setText("")
    }
}