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
import com.example.a491bproject.fragments.adapters.RecipeIngredientsAdapter
import com.example.a491bproject.models.IngredientModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.getValue


/**
 * Use the [RecipeIngredientsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RecipeIngredientsFragment : Fragment() {
    private lateinit var ingredients: MutableList<IngredientModel>
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RecipeIngredientsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_recipe_ingredients, container, false)
        adapter = RecipeIngredientsAdapter()
        val recipeID = arguments?.getString(getString(R.string.RecipeID))

        initializeRecyclerView(adapter, view)
        if (recipeID != null){
            populateRecyclerView(recipeID)
        }

        return view
    }

    private fun initializeRecyclerView(adapter: RecipeIngredientsAdapter, view: View){
        recyclerView = view.findViewById(R.id.rvRecipeIngredients)
        recyclerView.layoutManager = LinearLayoutManager(this.context)
        recyclerView.adapter = adapter
    }
    private fun populateRecyclerView(recipeID: String){
        val dbRef = FirebaseDatabase.getInstance().reference
        val query = dbRef.child("Ingredients/$recipeID").orderByChild("name")
        ingredients = mutableListOf<IngredientModel>()
        getIngredients(query, ::onAdded, ::onChanged, ::onError)
    }

    private fun getIngredients(query: Query, onAdded:(snapshot:DataSnapshot)->Unit, onChanged:(snapshot:DataSnapshot)->Unit, onError:(error:DatabaseError) ->Unit ){
        Log.d("GetIngredients", "Query Reference: ${query.ref.toString()}")
        query.addChildEventListener(object:ChildEventListener{
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
            val model = snapshot.getValue<IngredientModel>()
            Log.d("RecipeIngredientFrag", "OnAdded: $model")
            if (model!= null) ingredients.add(model)
            adapter.submitIngredients(ingredients)
        }

    }

    private fun onChanged(snapshot: DataSnapshot){
        if(snapshot.exists()){
            val model = snapshot.getValue<IngredientModel>()
            Log.d("RecipeIngredientFrag", "OnChanged: $model")
            if (model!= null) {
                val oldModel = ingredients.find{it.name == model.name}
                if (oldModel != null){
                    val position = ingredients.indexOf(oldModel)
                    ingredients[position] = model
                }
            }
            adapter.submitIngredients(ingredients)
        }
    }

    private fun onError(error: DatabaseError){
        Log.d("onCancelled","FirebaseChildListener ran into an error in RecipeIngredientsFragment. ${error.toString()}" )
    }



}