package com.example.a491bproject.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.a491bproject.fragments.CreateDescriptionFragment
import com.example.a491bproject.fragments.CreateIngredientsFragment
import com.example.a491bproject.fragments.CreateInstructionsFragment

class CreateRecipeFragmentStateAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle): FragmentStateAdapter(fragmentManager, lifecycle)  {
    private var fragment1 = CreateDescriptionFragment()
    private var fragment2 = CreateIngredientsFragment()
    private var fragment3 = CreateInstructionsFragment()

    override fun getItemCount(): Int {
        return 3 //Hardcoded. We have 3 tabtitles
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