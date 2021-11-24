package com.example.a491bproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val user = FirebaseAuth.getInstance();
        if(user.currentUser?.isEmailVerified == false){
            val verifyIntent = Intent(this, VerifyActivity::class.java)
            verifyIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(verifyIntent)

        }

        val emailId = intent.getStringExtra("emailID")
        //Navigate to Search Recipe Menu
        val searchRecipeBtn: Button = findViewById<Button>(R.id.searchRecipeBtn)
        searchRecipeBtn.setOnClickListener() {
            val searchRecipeIntent = Intent(this, SearchRecipeMenu::class.java)
            startActivity(searchRecipeIntent)

        }

        //Transitions from Main to Ingredients
        val ingredientsListBtn: Button = findViewById<Button>(R.id.ingredientsListBtn)
        ingredientsListBtn.setOnClickListener() {
            val ingredientsIntent = Intent(this, IngredientsActivity::class.java)
            startActivity(ingredientsIntent)

        }

        //Navigate to Ingredient Information
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

        //Navigate to User Recipes
        val userRecipesBtn: Button = findViewById<Button>(R.id.userRecipesBtn)
        userRecipesBtn.setOnClickListener{
            val userRecipesIntent = Intent(this, UserFirebaseRecipesActivity::class.java)
            startActivity(userRecipesIntent)
        }

        //Navigate to profile
        val settingsBtn: Button = findViewById<Button>(R.id.settingsBtn)
        settingsBtn.setOnClickListener{
            val settingsIntent = Intent(this, Settings::class.java)
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