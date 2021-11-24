package com.example.a491bproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.viewpager2.widget.ViewPager2
import com.example.a491bproject.adapters.RecipeInfoFragmentStateAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class RecipeFirebaseInfoActivity : AppCompatActivity() {

    private var tabTitles: ArrayList<String> = arrayListOf<String>("About", "Ingredients", "Instructions")
    private var recipeID = ""
    private var recipeTitle = "Custom Title Here"
    private val testID = "recipe01"

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("RecipeInfoActivity", "Creating RecipeFirebaseInfoActivity}")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_firebase_info)

        //Get values from UserFirebaseRecipesActivity
        receiveBundle()

        //Initialize UI Elements
        val viewPager = findViewById<ViewPager2>(R.id.view_pager)
        val tabLayout = findViewById<TabLayout>(R.id.tab_layout)
        val tvRecipeTitle = findViewById<TextView>(R.id.tvRecipeTitle)

        //Set Recipe Title
        tvRecipeTitle.text = recipeTitle

        //Set FragmentStateAdapter for viewPager2
        val adapter = RecipeInfoFragmentStateAdapter(supportFragmentManager,lifecycle)
        setUpAdapter(adapter, recipeID)
        viewPager.adapter = adapter

        //Set up Tab
        TabLayoutMediator(tabLayout, viewPager){
            tab, position -> tab.text = tabTitles[position]
        }.attach()


    }

    private fun setUpAdapter(adapter:RecipeInfoFragmentStateAdapter, id:String){
        adapter.setRecipeID(id)
        adapter.setRecipeKey(getString(R.string.RecipeID))
        adapter.setBundles()
    }

    private fun receiveBundle(){
        val extras = intent.extras
        if (extras != null) {
            recipeID = extras.getString(getString(R.string.RecipeID), testID)
            recipeTitle = extras.getString(getString(R.string.RecipeTitle), "Default Title Here")
        }
    }





}