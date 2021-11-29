package com.example.a491bproject.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.activityViewModels
import com.example.a491bproject.R
import com.example.a491bproject.models.Recipe
import com.example.a491bproject.models.RecipeAboutModel
import com.example.a491bproject.viewmodels.UpdateRecipeViewModel
import com.google.firebase.database.*
import com.google.firebase.database.ktx.getValue

class UpdateDescriptionFragment : Fragment() {
    private lateinit var viewUpdateRecipeDescription:View
    private lateinit var etRecipeTitle: EditText
    private lateinit var etRecipeDescription: EditText
    private val viewModel: UpdateRecipeViewModel by activityViewModels<UpdateRecipeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewUpdateRecipeDescription =  inflater.inflate(R.layout.fragment_update_description, container, false)
        etRecipeTitle = viewUpdateRecipeDescription.findViewById(R.id.etUpdateRecipeTitle)
        etRecipeDescription = viewUpdateRecipeDescription.findViewById(R.id.etUpdateRecipeDescription)
        val recipeID = arguments?.getString(getString(R.string.RecipeID))
        if (recipeID != null){
            populateEditTexts(recipeID)
        }
        addTextChangedListeners()
        return viewUpdateRecipeDescription
    }

    private fun addTextChangedListeners() {
        etRecipeTitle.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                Log.d("CreateTitle", "RecipeTitle afterTextChanged: ${p0.toString()}")
                viewModel.recipeTitle = etRecipeTitle.text.toString()
            }
        })

        etRecipeDescription.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                Log.d("CreateDescription", "RecipeDescription afterTextChanged: ${p0.toString()}")
                viewModel.description = etRecipeDescription.text.toString()
            }
        })
    }

    private fun populateEditTexts(recipeID:String){
        etRecipeTitle.setText(viewModel.recipeTitle)
        getRecipeAbout(recipeID)
    }

    //Data access function
    private fun getRecipeAbout(recipeID: String) {
        val dbRef = FirebaseDatabase.getInstance().reference
        val query = dbRef.child("AboutRecipe").child(recipeID).get().addOnSuccessListener {
            Log.d("UpdateDescriptionFrag", "Value: ${it.ref.toString()}")
            val model = it.getValue<RecipeAboutModel>()
            if (model != null){
                viewModel.description = model.description
                etRecipeDescription.setText(viewModel.description)
            }
            else {
                Log.e("UpdateDescriptionFrag", "AboutModel was null.")
            }
        }.addOnFailureListener {
            Log.e(
                "firebase",
                "Error getting data from UpdateDescriptionFragment on recipeID:$recipeID"
            )
        }
    }

}