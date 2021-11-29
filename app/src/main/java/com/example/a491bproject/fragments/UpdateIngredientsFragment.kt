package com.example.a491bproject.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.a491bproject.R
import com.example.a491bproject.fragments.adapters.UpdateIngredientsAdapter
import com.example.a491bproject.fragments.interfaces.IngredientsListener
import com.example.a491bproject.models.IngredientModel
import com.example.a491bproject.viewmodels.UpdateRecipeViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.getValue

/**
 * A simple [Fragment] subclass.
 */
class UpdateIngredientsFragment : Fragment(), IngredientsListener {
    private lateinit var viewUpdateRecipeIngredients:View
    private lateinit var etIngredientName: EditText
    private lateinit var etIngredientAmount: EditText
    private lateinit var etIngredientUnit: EditText
    private lateinit var btnAddIngredient: Button
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: UpdateIngredientsAdapter

    private val viewModel: UpdateRecipeViewModel by activityViewModels()

    private var amount:String = ""
    private var unit:String = ""
    private var name:String = ""
    private var amountEntered:Boolean = false
    private var unitEntered:Boolean = false
    private var nameEntered:Boolean = false
    private var ingredentsInitialized: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewUpdateRecipeIngredients = inflater.inflate(R.layout.fragment_update_ingredients, container, false)
        etIngredientName = viewUpdateRecipeIngredients.findViewById(R.id.etUpdateRecipeIngredientName)
        etIngredientAmount = viewUpdateRecipeIngredients.findViewById(R.id.etUpdateRecipeIngredientAmount)
        etIngredientUnit = viewUpdateRecipeIngredients.findViewById(R.id.etUpdateRecipeIngredientUnit)
        btnAddIngredient = viewUpdateRecipeIngredients.findViewById(R.id.btnUpdateRecipeAddIngredient)
        btnAddIngredient.isEnabled = false

        initializeAdapter()
        initializeRecyclerView(adapter, viewUpdateRecipeIngredients)

        val recipeID = arguments?.getString(getString(R.string.RecipeID))
        if (recipeID != null){
            populateEditTexts(recipeID)
        }

        addTextChangedListeners()
        addOnClickListeners()
        return viewUpdateRecipeIngredients
    }

    override fun onIngredientsChanged(list: MutableList<IngredientModel>) {
        viewModel.ingredients = list
    }

    /**
     * Initialization functions to help encapsulate information.
     */
    private fun initializeAdapter() {
        adapter = UpdateIngredientsAdapter(this)
    }

    private fun initializeRecyclerView(adapter: UpdateIngredientsAdapter, view: View){
        recyclerView = view.findViewById(R.id.rvUpdateRecipeIngredients)
        recyclerView.layoutManager = LinearLayoutManager(this.context)
        recyclerView.adapter = adapter
    }

    private fun populateEditTexts(recipeID:String){
        val dbRef = FirebaseDatabase.getInstance().reference
        val query = dbRef.child("Ingredients").child(recipeID).addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if (!ingredentsInitialized){ //We don't want changes to add new ingredients or reset the components if user makes changes from a different device.
                    for (postSnapshot in snapshot.children){
                        val model = postSnapshot.getValue<IngredientModel>()
                        if (model != null){
                            Log.d("UpdateDescriptionFrag", "Reference: ${postSnapshot.ref}")
                            adapter.submitIngredient(model)
                        }
                    }
                    ingredentsInitialized = true
                }

            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("UpdateIngredients","An error occured when trying to retrieve data from Ingredients")
            }

        })
    }




    /**
     * Past this are Functions concerned with the functionality of Edit Views.
     */
    private fun addTextChangedListeners(){
        etIngredientName.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                nameEntered = p0.toString().trim().isNotEmpty()
                tryEnableButton()
            }

            override fun afterTextChanged(p0: Editable?) {
                name = p0.toString()
            }

        })

        etIngredientAmount.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                amountEntered = p0.toString().trim().isNotEmpty()
                tryEnableButton()
            }

            override fun afterTextChanged(p0: Editable?) {
                amount = p0.toString()
            }
        })

        etIngredientUnit.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                unitEntered = p0.toString().trim().isNotEmpty()
                tryEnableButton()
            }

            override fun afterTextChanged(p0: Editable?) {
                unit = p0.toString()
            }
        })
    }

    private fun addOnClickListeners(){
        btnAddIngredient.setOnClickListener{
            val ingredient = IngredientModel(name,amount,unit)
            Log.d("UpdateIngredientAdpt","OnClickListener: Model contains: ${ingredient.toString()}")
            adapter.submitIngredient(ingredient)
            clearEditTexts()
        }
    }

    private fun clearEditTexts(){
        etIngredientName.text.clear()
        etIngredientUnit.text.clear()
        etIngredientAmount.text.clear()
    }


    private fun tryEnableButton(){
        //Log.d("TryEnableButton","$amountEntered $unitEntered $nameEntered")
        btnAddIngredient.isEnabled = amountEntered && unitEntered && nameEntered
    }




}