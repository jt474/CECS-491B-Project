package com.example.a491bproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val emailId = intent.getStringExtra("emailID")

        //Transitions from Main to Ingredients
        val ingredientsListBtn: Button = findViewById<Button>(R.id.ingredientsListBtn)
        ingredientsListBtn.setOnClickListener() {
            val ingredientsIntent = Intent(this, IngredientsActivity::class.java)
            startActivity(ingredientsIntent)

        }

        val ingredientsInfoBtn: Button = findViewById<Button>(R.id.ingredientsInfoBtn)
        ingredientsInfoBtn.setOnClickListener() {
            val ingredientsInfoIntent = Intent(this, IngredientsInformation::class.java)
            startActivity(ingredientsInfoIntent)

        }

        //Navigate to Create Recipe
        val createRecipeBtn: Button = findViewById<Button>(R.id.createRecipeBtn)
        createRecipeBtn.setOnClickListener{
            val createRecipeIntent = Intent(this, CreateRecipeActivity::class.java)
            startActivity(createRecipeIntent)
        }

        //Navigate to settings
        val settingsBtn: Button = findViewById<Button>(R.id.settingsBtn)
        settingsBtn.setOnClickListener{
            val settingsIntent = Intent(this, SettingActivity::class.java)
            settingsIntent.putExtra("emailID", emailId)
            startActivity(settingsIntent)
        }


        // Naming for menu
        val actionBar = supportActionBar

        if(actionBar != null){
            actionBar.title = "Main Menu"
        }
    }
}