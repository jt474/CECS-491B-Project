package com.example.a491bproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.example.a491bproject.adapters.UpdateRecipeFragmentStateAdapter
import com.example.a491bproject.interfaces.UpdateRecipeViewModelListener
import com.example.a491bproject.viewmodels.CreateRecipeViewModel
import com.example.a491bproject.viewmodels.UpdateRecipeViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class UpdateFirebaseRecipeActivity : AppCompatActivity(), UpdateRecipeViewModelListener {

    private var recipeID = ""
    private val tabTitles: ArrayList<String> = arrayListOf<String>("About", "Ingredients", "Instructions")
    private lateinit var mViewModel: UpdateRecipeViewModel
    private lateinit var btnUpdateRecipe: Button
    private lateinit var adapter:UpdateRecipeFragmentStateAdapter
    private lateinit var viewPager:ViewPager2
    private lateinit var tabLayout:TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_firebase_recipe)

        receiveBundle()

        //Initialize UI Elements
        viewPager = findViewById(R.id.view_pager_UpdateRecipe)
        tabLayout = findViewById(R.id.tab_layout_UpdateRecipe)
        initializeUpdateButton()
        initializeViewModel()

        //Set FragmentStateAdapter for ViewPager2
        adapter = UpdateRecipeFragmentStateAdapter(getString(R.string.RecipeID), recipeID, supportFragmentManager,lifecycle)
        viewPager.adapter = adapter

        //Set up Tabs
        TabLayoutMediator(tabLayout, viewPager){
            tab, position ->tab.text = tabTitles[position]
        }.attach()
    }

    private fun receiveBundle() {
        val extras = intent.extras
        if (extras != null) {
            recipeID = extras.getString(getString(R.string.RecipeID), getString(R.string.RecipeID))
            Log.d("UpdateRecipe","received recipeID:$recipeID")
        }
    }

    override fun onChanged() {
        btnUpdateRecipe.isEnabled = mViewModel.isNotMissingInfo()
    }

    private fun initializeUpdateButton(){
        btnUpdateRecipe = findViewById(R.id.btnUpdateRecipe)
        btnUpdateRecipe.isEnabled = false
        btnUpdateRecipe.setOnClickListener{

        }
    }

    private fun initializeViewModel(){
        mViewModel = ViewModelProvider(this).get(UpdateRecipeViewModel::class.java)
        mViewModel.setViewModelListener(this)
    }

    private fun updateToRealtimeDatabase(){

    }

}