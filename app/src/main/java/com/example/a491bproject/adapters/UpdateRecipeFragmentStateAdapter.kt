package com.example.a491bproject.adapters

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.a491bproject.fragments.*
//get recipeKey acquired from R.string.RecipeID
class UpdateRecipeFragmentStateAdapter(recipeKey:String, recipeID:String, fragmentManager: FragmentManager, lifecycle: Lifecycle): FragmentStateAdapter(fragmentManager, lifecycle) {

    private val recipeKey = recipeKey
    private val recipeID = recipeID
    private var descriptionFragment = UpdateDescriptionFragment()
    private var ingredientsFragment = UpdateIngredientsFragment()
    private var instructionsFragment = UpdateInstructionsFragment()

    init{
        val bundle = Bundle()
        bundle.putString(recipeKey,recipeID)
        descriptionFragment.arguments = bundle
        ingredientsFragment.arguments = bundle
        instructionsFragment.arguments = bundle
    }


    override fun getItemCount(): Int {
        return 3//Hardcoded. We have 3 tab titles
    }

    override fun createFragment(position: Int): Fragment {
        when(position){
            0-> return descriptionFragment
            1-> return ingredientsFragment
            2-> return instructionsFragment
        }
        return descriptionFragment
    }
}