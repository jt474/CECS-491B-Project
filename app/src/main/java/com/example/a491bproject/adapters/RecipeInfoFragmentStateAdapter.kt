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
//recipeKey should be acquired from resources.
class RecipeInfoFragmentStateAdapter(recipeKey:String, recipeID:String, fragmentManager: FragmentManager, lifecycle: Lifecycle): FragmentStateAdapter(fragmentManager, lifecycle) {

    private val recipeKey = recipeKey
    private val recipeID = recipeID
    private var fragment1 = RecipeAboutFragment()
    private var fragment2 = RecipeIngredientsFragment()
    private var fragment3 = RecipeInstructionsFragment()

    init{
        val bundle = Bundle()
        bundle.putString(recipeKey,recipeID)
        fragment1.arguments = bundle
        fragment2.arguments = bundle
        fragment3.arguments = bundle
    }

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

}