package com.example.a491bproject.fragments

import android.icu.util.Calendar
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.example.a491bproject.R
import com.example.a491bproject.models.UserRecipesModel
import com.example.a491bproject.viewmodels.CreateRecipeViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import java.text.SimpleDateFormat
import java.util.*

class CreateDescriptionFragment : Fragment() {
    private lateinit var viewCreateRecipeDescription:View
    private lateinit var etRecipeTitle: EditText
    private lateinit var etRecipeDescription: EditText
    private val viewModel: CreateRecipeViewModel by activityViewModels<CreateRecipeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        viewCreateRecipeDescription = inflater.inflate(R.layout.fragment_create_description, container, false)
        etRecipeTitle = viewCreateRecipeDescription.findViewById(R.id.etCreateRecipeTitle)
        etRecipeDescription = viewCreateRecipeDescription.findViewById(R.id.etCreateRecipeDescription)


        addTextChangedListeners()

        return viewCreateRecipeDescription
    }

    private fun addTextChangedListeners(){
        etRecipeTitle.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                Log.d("CreateTitle","RecipeTitle afterTextChanged: ${p0.toString()}")
                viewModel.setTitle(etRecipeTitle.text.toString(),"CreateDescriptionFragment.addTextChangedListener")
            }
        })
        
        etRecipeDescription.addTextChangedListener(object:TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
               Log.d("CreateDescription", "RecipeDescription afterTextChanged: ${p0.toString()}")
                viewModel.setDescription(etRecipeDescription.text.toString(),"CreateDescriptionFragment.addTextChangedListener")
            }
        })
    }






}