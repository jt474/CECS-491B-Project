package com.example.a491bproject

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.example.a491bproject.adapters.CreateRecipeFragmentStateAdapter
import com.example.a491bproject.adapters.RecipeInfoFragmentStateAdapter
import com.example.a491bproject.viewmodels.CreateRecipeViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class CreateFirebaseRecipeActivity : AppCompatActivity() {

    private var tabTitles: ArrayList<String> = arrayListOf<String>("About", "Ingredients", "Instructions", "Review")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_firebase_recipe)

        //Initialize UI Elements
        val viewPager = findViewById<ViewPager2>(R.id.view_pager_CreateRecipe)
        val tabLayout = findViewById<TabLayout>(R.id.tab_layout_CreateRecipe)
        val mViewModel = ViewModelProvider(this).get(CreateRecipeViewModel::class.java)

        val adapter = CreateRecipeFragmentStateAdapter(supportFragmentManager,lifecycle)
        viewPager.adapter = adapter

        TabLayoutMediator(tabLayout, viewPager){
                tab, position -> tab.text = tabTitles[position];

        }.attach()
    }


}