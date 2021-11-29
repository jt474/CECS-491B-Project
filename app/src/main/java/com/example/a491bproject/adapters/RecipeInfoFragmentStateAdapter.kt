package com.example.a491bproject.adapters

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.a491bproject.fragments.RecipeIngredientsFragment
import com.example.a491bproject.fragments.RecipeInstructionsFragment
import com.example.a491bproject.fragments.RecipeAboutFragment

class RecipeInfoFragmentStateAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle): FragmentStateAdapter(fragmentManager, lifecycle) {

    private lateinit var recipeKey:String
    private lateinit var recipeID:String
    private var fragment1 = RecipeAboutFragment()
    private var fragment2 = RecipeIngredientsFragment()
    private var fragment3 = RecipeInstructionsFragment()

    override fun getItemCount(): Int {
        return 3 //Hardcoded... we have 3 tab titles
    }

    override fun createFragment(position: Int): Fragment {
        when(position){
            0-> return fragment1
            1-> return fragment2
            2-> return fragment3
        }
        return fragment1
    }

    fun setRecipeKey(key:String){
        Log.d("SetRecipeKey","RecipeKey Set")
        recipeKey= key
    }
    fun setRecipeID(id:String){
        Log.d("SetRecipeID","RecipeIDSet")
        recipeID = id
    }
    fun setBundles(){
        nutritionBundle()
        ingredientsBundle()
        instructionsBundle()
    }

    private fun nutritionBundle(){
        val bundle = Bundle()
        bundle.putString(recipeKey,recipeID)
        val frag = RecipeAboutFragment()
        frag.arguments = bundle
        fragment1 = frag
    }

    private fun ingredientsBundle(){
        val bundle = Bundle()
        bundle.putString(recipeKey,recipeID)
        val frag = RecipeIngredientsFragment()
        frag.arguments = bundle
        fragment2 = frag
    }

    private fun instructionsBundle(){
        val bundle = Bundle()
        bundle.putString(recipeKey,recipeID)
        val frag = RecipeInstructionsFragment()
        frag.arguments = bundle
        fragment3 = frag
    }

}