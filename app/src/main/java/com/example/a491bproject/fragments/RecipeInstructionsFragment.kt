package com.example.a491bproject.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.a491bproject.DBHandlers.RecipeDAO
import com.example.a491bproject.R
import com.example.a491bproject.fragments.adapters.RecipeInstructionsAdapter
import com.example.a491bproject.models.IngredientModel
import com.example.a491bproject.models.InstructionModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.getValue

/**
 * Use the [RecipeInstructionsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RecipeInstructionsFragment : Fragment() {
    private lateinit var auth: FirebaseAuth
    private lateinit var dbHandler: RecipeDAO
    private lateinit var instructions: MutableList<InstructionModel>
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RecipeInstructionsAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_recipe_instructions, container, false)
        adapter = RecipeInstructionsAdapter()
        val recipeID = arguments?.getString(getString(R.string.RecipeID))
        Log.d("InstructionsFragment", "RecipeID is $recipeID")
        initializeRecyclerView(adapter, view)
        if (recipeID != null){
            Log.d("InstructionsFragment", "Populating RecyclerView")
            populateRecyclerView(recipeID)
        }

        return view
    }

    private fun initializeRecyclerView(adapter: RecipeInstructionsAdapter, view: View){
        recyclerView = view.findViewById(R.id.rvRecipeInstructions)
        recyclerView.layoutManager = LinearLayoutManager(this.context)
        recyclerView.adapter = adapter
    }

    private fun populateRecyclerView(recipeID: String){
        val dbRef = FirebaseDatabase.getInstance().reference
        val query = dbRef.child("Instructions/$recipeID").orderByChild("Step")
        instructions = mutableListOf<InstructionModel>()
        getInstructions(query, ::onAdded, ::onChanged, ::onError)
    }

    private fun getInstructions(query: Query, onAdded:(snapshot: DataSnapshot)->Unit, onChanged:(snapshot: DataSnapshot)->Unit, onError:(error: DatabaseError) ->Unit ){
        Log.d("GetInstructions", "Query Reference: ${query.ref.toString()}")
        query.addChildEventListener(object: ChildEventListener {
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                Log.d("GetInstructionsAdded", "${snapshot.key} ${snapshot.value}")
                onAdded(snapshot)
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
                Log.d("GetInstructionsChanged", "${snapshot.key} ${snapshot.getValue<IngredientModel>()}")
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
            val model = snapshot.getValue<InstructionModel>()
            Log.d("onAdded", "$model")
            if (model!= null) instructions.add(model)
            adapter.submitInstructions(instructions)
        }

    }

    private fun onChanged(snapshot: DataSnapshot){
        if(snapshot.exists()){
            val model = snapshot.getValue<InstructionModel>()
            if (model!= null) {
                val oldModel = instructions.find{it.number == model.number} //step number is unique identifier
                //Find old model and replace with new model.
                if (oldModel != null){
                    val position = instructions.indexOf(oldModel)
                    instructions[position] = model
                }
            }
            adapter.submitInstructions(instructions)
        }
    }

    private fun onError(error: DatabaseError){
        Log.d("onCancelled","FirebaseChildListener ran into an error in RecipeInstructionsFragment. ${error.toString()}" )
    }



}