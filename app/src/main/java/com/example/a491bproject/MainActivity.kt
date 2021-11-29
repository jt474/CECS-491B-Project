package com.example.a491bproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.a491bproject.adapters.ButtonAdapter
import com.example.a491bproject.models.Button
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity(), ButtonAdapter.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val user = FirebaseAuth.getInstance();
        if (user.currentUser?.isEmailVerified == false) {
            val verifyIntent = Intent(this, VerifyActivity::class.java)
            verifyIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(verifyIntent)
        }

//        val emailId = intent.getStringExtra("emailID")

        val recyclerView: RecyclerView = findViewById(R.id.rv_main)
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        recyclerView.adapter = ButtonAdapter(this, fetchList(), this)

//        //Navigate to Search Recipe Menu
//        val searchRecipeBtn: Button = findViewById<Button>(R.id.searchRecipeBtn)
//        searchRecipeBtn.setOnClickListener() {
//            val searchRecipeIntent = Intent(this, SearchRecipeMenu::class.java)
//            startActivity(searchRecipeIntent)
//
//        }
//
//        //Transitions from Main to Ingredients
//        val ingredientsListBtn: Button = findViewById<Button>(R.id.ingredientsListBtn)
//        ingredientsListBtn.setOnClickListener() {
//            val ingredientsIntent = Intent(this, IngredientsActivity::class.java)
//            startActivity(ingredientsIntent)
//
//        }
//
//        //Navigate to Ingredient Information
//        val ingredientsInfoBtn: Button = findViewById<Button>(R.id.ingredientsInfoBtn)
//        ingredientsInfoBtn.setOnClickListener() {
//            val ingredientsInfoIntent = Intent(this, IngredientsInformation::class.java)
//            startActivity(ingredientsInfoIntent)
//
//        }
//
//        //Navigate to Create Recipe
//        val createRecipeBtn: Button = findViewById<Button>(R.id.createRecipeBtn)
//        createRecipeBtn.setOnClickListener{
//            val createRecipeIntent = Intent(this, CreateFirebaseRecipeActivity::class.java)
//            startActivity(createRecipeIntent)
//        }
//
//        //Navigate to User Recipes
//        val userRecipesBtn: Button = findViewById<Button>(R.id.userRecipesBtn)
//        userRecipesBtn.setOnClickListener{
//            val userRecipesIntent = Intent(this, UserFirebaseRecipesActivity::class.java)
//            startActivity(userRecipesIntent)
//        }
//
//        //Navigate to profile
//        val settingsBtn: Button = findViewById<Button>(R.id.settingsBtn)
//        settingsBtn.setOnClickListener{
//            val settingsBtn: Button = findViewById<Button>(R.id.settingsBtn)
////        settingsBtn.setOnClickListener{
////            val settingsIntent = Intent(this, Settings::class.java)
////            settingsIntent.putExtra("emailID", emailId)
////            startActivity(settingsIntent)
////        }
//        }


        // Naming for menu
        val actionBar = supportActionBar

        if (actionBar != null) {
            actionBar.title = "Main Menu"
        }
    }

    private fun fetchList(): ArrayList<Button> {
        val list = arrayListOf<Button>()

        list.add(Button(R.drawable.search_recipe, "Search Recipe"))
        list.add(Button(R.drawable.ingredient_list, "Ingredient List"))
        list.add(Button(R.drawable.your_recipe, "Your Recipes"))
        list.add(Button(R.drawable.ingredient_info, "Ingredient Information"))
        list.add(Button(R.drawable.create_recipe, "Create Recipe"))
        list.add(Button(R.drawable.settings, "Settings"))

        return list
    }

    override fun onClickListener(data: Int) {
        val emailId = intent.getStringExtra("emailID")
        when (data) {
            0 -> {
                val searchRecipeIntent = Intent(this, SearchRecipeMenu::class.java)
                startActivity(searchRecipeIntent)
            }
            1 -> {
                val ingredientsIntent = Intent(this, IngredientsActivity::class.java)
                startActivity(ingredientsIntent)
            }
            2 -> {
                val userRecipesIntent = Intent(this, UserFirebaseRecipesActivity::class.java)
                startActivity(userRecipesIntent)
            }
            3 -> {
                val ingredientsInfoIntent = Intent(this, IngredientsInformation::class.java)
                startActivity(ingredientsInfoIntent)
            }
            4 -> {
                val createRecipeIntent = Intent(this, CreateFirebaseRecipeActivity::class.java)
                startActivity(createRecipeIntent)
            }
            5 -> {
                val settingsIntent = Intent(this, Settings::class.java)
                settingsIntent.putExtra("emailID", emailId)
                startActivity(settingsIntent)
            }
        }
    }
}