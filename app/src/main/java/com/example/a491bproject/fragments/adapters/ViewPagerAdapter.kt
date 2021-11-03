package com.example.a491bproject.fragments.adapters

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.a491bproject.fragments.RecipeIngredientsFragment
import com.example.a491bproject.fragments.RecipeNutritionFragment
import com.example.a491bproject.fragments.RecipeInstructionsFragment


class ViewPagerAdapter(fragmentActivity: FragmentActivity): FragmentStateAdapter(fragmentActivity)  {

    private val mFragmentList = ArrayList<Fragment>()
    private val mFragmentTitleList = ArrayList<String>()

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0->RecipeNutritionFragment.newInstance()
            1->RecipeIngredientsFragment.newInstance()
            2->RecipeInstructionsFragment.newInstance()
            else-> RecipeNutritionFragment.newInstance()
        }
    }
}