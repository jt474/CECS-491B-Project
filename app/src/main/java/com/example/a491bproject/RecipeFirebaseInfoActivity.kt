package com.example.a491bproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.example.a491bproject.adapters.RecipeInfoFragmentStateAdapter
import com.example.a491bproject.fragments.RecipeIngredientsFragment
import com.example.a491bproject.fragments.RecipeInstructionsFragment
import com.example.a491bproject.fragments.RecipeNutritionFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class RecipeFirebaseInfoActivity : AppCompatActivity() {

    private var tabTitles: ArrayList<String> = arrayListOf<String>("Nutrition", "Ingredients", "Instructions")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_firebase_info)

        val recipeID = intent.getStringExtra("")
        val testID = "recipe01"

        val viewPager = findViewById<ViewPager2>(R.id.view_pager)
        val tabLayout = findViewById<TabLayout>(R.id.tab_layout)
        val adapter = RecipeInfoFragmentStateAdapter(supportFragmentManager,lifecycle)
        adapter.setRecipeID(testID)
        adapter.setRecipeKey(getString(R.string.RecipeID))
        adapter.setBundles()
        viewPager.adapter = adapter

        TabLayoutMediator(tabLayout, viewPager){
            tab, position -> tab.text = tabTitles[position]
        }.attach()


    }





}