package com.example.a491bproject

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream

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

                /* Write to an internal text file */
                val ingredientsFile : FileOutputStream
                val contents = ingredient.text.toString() + " , " + quantity.text.toString()
                Toast.makeText(this, "Adding $contents", Toast.LENGTH_SHORT).show()
                Log.d("IngredientActivity", "contents: $contents")
                try {
                    Log.d("IngredientsActivity", "attempt to create a text file")
                    ingredientsFile = openFileOutput("ingredients.txt", Context.MODE_PRIVATE)
                    ingredientsFile.write(contents.toByteArray())
                } catch (e : FileNotFoundException) {
                    e.printStackTrace()
                }
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

    public fun viewIngredients(v: View) {}
}