package com.example.a491bproject

import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.a491bproject.databinding.ActivityUserFirebaseRecipesBinding
import com.example.a491bproject.adapters.UserRecipesAdapter as UserRecipesAdapter
import com.example.a491bproject.models.UserRecipesModel as UserRecipesModel

class UserFirebaseRecipesActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityUserFirebaseRecipesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_firebase_recipes)

       // binding = ActivityUserFirebaseRecipesBinding.inflate(layoutInflater)
       // setContentView(binding.root)

       var testRecipesList = mutableListOf<UserRecipesModel>(
           UserRecipesModel("Apple Pie",false),
           UserRecipesModel("Misery",false),
           UserRecipesModel("Llamas",false),
           UserRecipesModel("Rough",false),
           UserRecipesModel("Apple Pie",false))

        val adapter = UserRecipesAdapter(testRecipesList)
        val recyclerView = findViewById<RecyclerView>(R.id.rvUserRecipes)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)


    }


}
