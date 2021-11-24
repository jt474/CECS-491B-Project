package com.example.a491bproject.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.a491bproject.DBHandlers.RecipeDAO
import com.example.a491bproject.R
import com.example.a491bproject.fragments.adapters.RecipeInstructionsAdapter
import com.example.a491bproject.models.IngredientModel
import com.example.a491bproject.models.InstructionModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.Query
import com.google.firebase.database.ktx.getValue

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [RecipeAboutFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RecipeAboutFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_recipe_about, container, false)

        return view

    }

    private fun getRecipeAbout(query: Query, onAdded:(snapshot: DataSnapshot)->Unit, onChanged:(snapshot: DataSnapshot)->Unit, onError:(error: DatabaseError) ->Unit ){
        Log.d("GetIngredients", "Query Reference: ${query.ref.toString()}")
        query.addChildEventListener(object: ChildEventListener {
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                Log.d("GetIngredientsAdded", "${snapshot.key} ${snapshot.value}")
                onAdded(snapshot)
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
                Log.d("GetIngredientsChanged", "${snapshot.key} ${snapshot.getValue<IngredientModel>()}")
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

    private fun onAdded(snapshot: DataSnapshot){
        if(snapshot.exists()){
            Log.d("RecipeAboutFragment", "OnAdded: ")

        }

    }

    private fun onChanged(snapshot: DataSnapshot){
        if(snapshot.exists()){
            Log.d("RecipeAboutFragment", "OnChanged: ")

        }
    }

    private fun onError(error: DatabaseError){
        Log.d("RecipeABoutFragment","FirebaseChildListener ran into an error in RecipeIngredientsFragment. ${error.toString()}" )
    }
}