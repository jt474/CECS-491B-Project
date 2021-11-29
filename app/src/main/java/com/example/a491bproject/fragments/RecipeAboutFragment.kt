package com.example.a491bproject.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.a491bproject.DBHandlers.RecipeDAO
import com.example.a491bproject.R
import com.example.a491bproject.fragments.adapters.RecipeInstructionsAdapter
import com.example.a491bproject.models.IngredientModel
import com.example.a491bproject.models.InstructionModel
import com.example.a491bproject.models.RecipeAboutModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.getValue
/**
 * A simple [Fragment] subclass.
 * Use the [RecipeAboutFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RecipeAboutFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private lateinit var tvDateUpdated: TextView
    private lateinit var tvDescription: TextView
    private lateinit var viewRecipeAbout: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewRecipeAbout = inflater.inflate(R.layout.fragment_recipe_about, container, false)
        tvDateUpdated = viewRecipeAbout.findViewById<TextView>(R.id.tvLastUpdatedData)
        tvDescription = viewRecipeAbout.findViewById<TextView>(R.id.tvDescriptionData)
        viewRecipeAbout.visibility = View.INVISIBLE
        val recipeID = arguments?.getString(getString(R.string.RecipeID))
        if (recipeID != null){
            populateTextViews(recipeID)
        }
        return viewRecipeAbout

    }

    private fun populateTextViews(recipeID: String){
        val dbRef = FirebaseDatabase.getInstance().reference
        val query = dbRef.child("AboutRecipe").orderByKey().equalTo(recipeID)
        getRecipeAbout(query, ::onAdded, ::onChanged, ::onError)
    }

    private fun setTextViews(model: RecipeAboutModel) {
        tvDateUpdated.text = model.dateUpdated
        tvDescription.text = model.description
        viewRecipeAbout.visibility = View.VISIBLE
    }

    //Data access function
    private fun getRecipeAbout(query: Query, onAdded:(snapshot: DataSnapshot)->Unit, onChanged:(snapshot: DataSnapshot)->Unit, onError:(error: DatabaseError) ->Unit ){
        Log.d("GetRecipeAbout", "Query Reference: ${query.ref.toString()}")
        query.addChildEventListener(object: ChildEventListener {
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                Log.d("RecipeAboutFragment", "GetAboutRecipeAdded called at Key: ${snapshot.key}")
                onAdded(snapshot)
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
                Log.d("RecipeAboutFragment", "GetAboutRecipeChanged called at Key: ${snapshot.key}")
                onChanged(snapshot)
            }

            override fun onChildRemoved(snapshot: DataSnapshot) {
            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
            }

            override fun onCancelled(error: DatabaseError) {
                onError(error)
            }

        })
    }

    //Callback methods passed through as function parameters
    private fun onAdded(snapshot: DataSnapshot){
        if(snapshot.exists()){
            val model = snapshot.getValue<RecipeAboutModel>()
            Log.d("RecipeAboutFragment", "OnAdded: ${model.toString()}")
            if (model!= null) setTextViews(model)
        }

    }

    private fun onChanged(snapshot: DataSnapshot){
        if(snapshot.exists()){
            val model = snapshot.getValue<RecipeAboutModel>()
            Log.d("RecipeAboutFragment", "OnChanged: ${model.toString()}")
            if (model!= null) setTextViews(model)
        }
    }

    private fun onError(error: DatabaseError){
        Log.d("RecipeAboutFragment","FirebaseChildListener ran into an error in RecipeAboutFragment. ${error.toString()}" )
    }
}