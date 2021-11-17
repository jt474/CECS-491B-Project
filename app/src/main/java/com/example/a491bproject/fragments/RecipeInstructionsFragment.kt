package com.example.a491bproject.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.a491bproject.DBHandlers.RecipeDAO
import com.example.a491bproject.R
import com.example.a491bproject.fragments.adapters.RecipeInstructionsAdapter
import com.example.a491bproject.models.UserRecipesModel
import com.google.firebase.auth.FirebaseAuth

/**
 * Use the [RecipeInstructionsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RecipeInstructionsFragment : Fragment() {
    private lateinit var auth: FirebaseAuth
    private lateinit var dbHandler: RecipeDAO
    private lateinit var testRecipesList: MutableList<UserRecipesModel>
    private lateinit var recyclerView: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_recipe_instructions, container, false)
        val adapter = RecipeInstructionsAdapter()
        recyclerView = view.findViewById(R.id.rvRecipeInstructions)
        recyclerView.layoutManager = LinearLayoutManager(this.context)
        recyclerView.adapter = adapter
        return view
    }





}