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

//        val button = findViewById<TextView>(R.id.buttonAddIngredient)
//        button.setOnClickListener {
////            val db = DataBaseHandler(this)
//            val ingredient: TextView = findViewById(R.id.editTextIngredient)
//            val quantity: TextView = findViewById(R.id.editTextQuantity)
//            if (ingredient.text.toString().isNotEmpty() &&
//                quantity.text.toString().isNotEmpty()
//            ) {
//                val user = IngredientsActivity.User(
//                    ingredient.text.toString(),
//                    quantity.text.toString().toDouble()
//                )
////                db.insertData(user)
//
//                /* Write to an internal text file */
//                val ingredientsFile : FileOutputStream
//                val contents = ingredient.text.toString() + " , " + quantity.text.toString()
//                Toast.makeText(this, "Adding $contents", Toast.LENGTH_SHORT).show()
//                Log.d("IngredientActivity", "contents: $contents")
//                try {
//                    Log.d("IngredientsActivity", "attempt to create a text file")
//                    ingredientsFile = openFileOutput("ingredients.txt", Context.MODE_PRIVATE)
//                    ingredientsFile.write(contents.toByteArray())
//                } catch (e : FileNotFoundException) {
//                    e.printStackTrace()
//                }
//                clearField()
//            } else {
//                Toast.makeText(this, "Input Ingredient and Quantity", Toast.LENGTH_SHORT).show()
//            }
//        }

        val button = findViewById<TextView>(R.id.buttonAddIngredient)
        button.setOnClickListener {
            val db = DataBaseHandler(this)
            val ingredient: TextView = findViewById(R.id.editTextIngredient)
            val quantity: TextView = findViewById(R.id.editTextQuantity)
            val ingredientString = ingredient.text.toString()
            val quantityString = quantity.text.toString()
            if (ingredient.text.toString() != "" &&
                quantity.text.toString() != ""
            ) {
//                val user = IngredientsActivity.User(
//                    ingredient.text.toString(),
//                    quantity.text.toString().toInt()
//                )
                db.addIngredient(ingredientString, quantityString)
                val contents = "$ingredient , $quantity"
                Toast.makeText(this, "Adding $contents", Toast.LENGTH_SHORT).show()
                clearField()
                val textView = findViewById<TextView>(R.id.textView4)
                val separator = "\n"
                textView.text = db.viewIngredient().joinToString(separator)
                //textView.text = "Success"
            } else {
                Toast.makeText(this, "Input Ingredient and Quantity", Toast.LENGTH_SHORT).show()
            }
        }

        val button2 = findViewById<TextView>(R.id.buttonUpdateIngredient)
        button2.setOnClickListener {
            val db = DataBaseHandler(this)
            val ingredient: TextView = findViewById(R.id.editTextIngredient)
            val quantity: TextView = findViewById(R.id.editTextQuantity)
            val ingredientString = ingredient.text.toString()
            val quantityString = quantity.text.toString()
            if (ingredient.text.toString() != "" &&
                quantity.text.toString() != ""
            ) {
//                val user = IngredientsActivity.User(
//                    ingredient.text.toString(),
//                    quantity.text.toString().toInt()
//                )
                db.deleteIngredient(ingredientString)
                db.addIngredient(ingredientString, quantityString)
                val contents = "$ingredient , $quantity"
                Toast.makeText(this, "Updating $contents", Toast.LENGTH_SHORT).show()
                clearField()
                val textView = findViewById<TextView>(R.id.textView4)
                val separator = "\n"
                textView.text = db.viewIngredient().joinToString(separator)
            } else {
                Toast.makeText(this, "Input Ingredient and Quantity", Toast.LENGTH_SHORT).show()
            }
        }

        val button3 = findViewById<TextView>(R.id.buttonDeleteIngredient)
        button3.setOnClickListener{
            val db = DataBaseHandler(this)
            val ingredient: TextView = findViewById(R.id.editTextIngredient)
            val quantity: TextView = findViewById(R.id.editTextQuantity)
            val ingredientString = ingredient.text.toString()
            if (ingredient.text.toString() != ""
            ) {
//                val user = IngredientsActivity.User(
//                    ingredient.text.toString(),
//                    quantity.text.toString().toInt()
//                )
                db.deleteIngredient(ingredientString)
                val contents = "$ingredient"
                Toast.makeText(this, "Deleting $contents", Toast.LENGTH_SHORT).show()
                clearField()
                val textView = findViewById<TextView>(R.id.textView4)
                val separator = "\n"
                textView.text = db.viewIngredient().joinToString(separator)
            } else {
                Toast.makeText(this, "Input Ingredient and Quantity", Toast.LENGTH_SHORT).show()
            }
        }

        val button4 = findViewById<TextView>(R.id.buttonViewIngredient)
        button4.setOnClickListener{
            val db = DataBaseHandler(this)
            val textView = findViewById<TextView>(R.id.textView4)
            val separator = "\n"
            textView.text = db.viewIngredient().joinToString(separator)
        }

        val button5 = findViewById<TextView>(R.id.buttonClearIngredient)
        button5.setOnClickListener{
            val db = DataBaseHandler(this)
            db.clearIngredient()
            val textView = findViewById<TextView>(R.id.textView4)
            val separator = "\n"
            textView.text = db.viewIngredient().joinToString(separator)
        }
    }

//    class User(ingredientInput: String, quantityInput: Int) {
//        val ingredient = ingredientInput
//        val quantity = quantityInput
//    }

    private fun clearField() {
        findViewById<EditText>(R.id.editTextIngredient).text.clear()
        findViewById<EditText>(R.id.editTextQuantity).text.clear()
    }

    private fun viewIngredients() {
        findViewById<Button>(R.id.button_view_ingredients).setOnClickListener {
//            Toast.makeText(this, "I hope this work lol", Toast.LENGTH_SHORT).show()
            val myIngredientsIntent = Intent(this, MyIngredientsActivity::class.java)
            startActivity(myIngredientsIntent)
        }
    }
}