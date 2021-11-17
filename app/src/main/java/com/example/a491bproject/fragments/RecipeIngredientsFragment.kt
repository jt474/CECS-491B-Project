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
import com.example.a491bproject.models.UserRecipesModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.getValue


/**
 * Use the [RecipeIngredientsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RecipeIngredientsFragment : Fragment() {
    private lateinit var auth: FirebaseAuth
    private lateinit var dbHandler: RecipeDAO
    private lateinit var ingredientList: MutableList<IngredientModel>
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
        ingredientList = mutableListOf<IngredientModel>()
        getIngredients(query, ::onAdded, ::onChanged, ::onError)
    }

    private fun getIngredients(query: Query, onAdded:(snapshot:DataSnapshot)->Unit, onChanged:(snapshot:DataSnapshot)->Unit, onError:(error:DatabaseError) ->Unit ){
        Log.d("GetIngredients", "Query: ${query.ref.toString()}")
        query.addChildEventListener(object:ChildEventListener{
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                Log.d("DatasnapshotAdded", "${snapshot.key} ${snapshot.value}")
                onAdded(snapshot)
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
                Log.d("DatasnapshotChanged", "${snapshot.key} ${snapshot.getValue<IngredientModel>()}")
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
            val name = snapshot.child("name").getValue<String>()
            val amount = snapshot.child("amount").getValue<String>()
            val unit = snapshot.child("unit").getValue<String>()
            val model = IngredientModel(name!!,amount!!,unit!!)
            Log.d("onAdded", "$ $model $name $amount $unit")
            if (model!= null) ingredientList.add(model)
            adapter.submitIngredients(ingredientList)
        }

    }

    private fun onChanged(snapshot: DataSnapshot){
        if(snapshot.exists()){
            val model = snapshot.getValue<IngredientModel>()
            if (model!= null) {
                val oldModel = ingredientList.find{it.name == model.name}
                if (oldModel != null){
                    val position = ingredientList.indexOf(oldModel)
                    ingredientList[position] = model
                }
            }
            adapter.submitIngredients(ingredientList)
        }
    }

    private fun onError(error: DatabaseError){
        Log.d("onCancelled","FirebaseChildListener ran into an error. ${error.toString()}" )
    }



}