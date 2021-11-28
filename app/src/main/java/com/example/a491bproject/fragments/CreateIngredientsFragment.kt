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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.a491bproject.R
import com.example.a491bproject.fragments.adapters.CreateIngredientsAdapter
import com.example.a491bproject.models.IngredientModel

class CreateIngredientsFragment : Fragment() {
    private lateinit var viewCreateRecipeIngredients:View
    private lateinit var etIngredientName: EditText
    private lateinit var etIngredientAmount: EditText
    private lateinit var etIngredientUnit: EditText
    private lateinit var btnAddIngredient: Button
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CreateIngredientsAdapter

    private var amount:String = ""
    private var unit:String = ""
    private var name:String = ""
    private var amountEntered:Boolean = false
    private var unitEntered:Boolean = false
    private var nameEntered:Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewCreateRecipeIngredients =  inflater.inflate(R.layout.fragment_create_ingredients, container, false)
        etIngredientName = viewCreateRecipeIngredients.findViewById(R.id.etCreateRecipeIngredientName)
        etIngredientAmount = viewCreateRecipeIngredients.findViewById(R.id.etCreateRecipeIngredientAmount)
        etIngredientUnit = viewCreateRecipeIngredients.findViewById(R.id.etCreateRecipeIngredientUnit)
        btnAddIngredient = viewCreateRecipeIngredients.findViewById(R.id.btnCreateRecipeAddIngredient)
        btnAddIngredient.isEnabled = false
        adapter = CreateIngredientsAdapter()

        initializeRecyclerView(adapter, viewCreateRecipeIngredients)

        addTextChangedListeners()
        addOnClickListeners()
        return viewCreateRecipeIngredients
    }

    private fun initializeRecyclerView(adapter: CreateIngredientsAdapter, view: View){
        recyclerView = view.findViewById(R.id.rvCreateRecipeIngredients)
        recyclerView.layoutManager = LinearLayoutManager(this.context)
        recyclerView.adapter = adapter
    }

    private fun addTextChangedListeners() {
        etIngredientName.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(p0.toString().trim().isEmpty()){
                    nameEntered = false
                    tryEnableButton()
                } else {
                    nameEntered = true
                    tryEnableButton()
                }
            }
            override fun afterTextChanged(p0: Editable?) {
                Log.d("CreateIngredient","IngredientName afterTextChanged: ${p0.toString()}")
                name = p0.toString()
            }

        })

        etIngredientAmount.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(p0.toString().trim().isEmpty()){
                    amountEntered = false
                    tryEnableButton()
                } else {
                    amountEntered = true
                    tryEnableButton()
                }
            }

            override fun afterTextChanged(p0: Editable?) {
                Log.d("CreateIngredient","IngredientAmount afterTextChanged: ${p0.toString()}")
                amount = p0.toString()
            }

        })

        etIngredientUnit.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(p0.toString().trim().isEmpty()){
                    unitEntered = false
                    tryEnableButton()
                } else {
                    unitEntered = true
                    tryEnableButton()
                }
            }

            override fun afterTextChanged(p0: Editable?) {
                Log.d("CreateIngredient","IngredientUnit afterTextChanged: ${p0.toString()}")
                unit = p0.toString()
            }

        })
    }

    private fun addOnClickListeners(){
        btnAddIngredient.setOnClickListener{
            val ingredient = IngredientModel(name,amount,unit)
            Log.d("OnClickListener","CreateIngredientsFragment: Model contains: ${ingredient.toString()}")
            adapter.submitIngredient(ingredient)
            clearEditTexts()
        }
    }

    private fun tryEnableButton(){
        Log.d("TryEnableButton","$amountEntered $unitEntered $nameEntered")
        btnAddIngredient.isEnabled = amountEntered && unitEntered && nameEntered
    }

    private fun clearEditTexts(){
        etIngredientName.text.clear()
        etIngredientUnit.text.clear()
        etIngredientAmount.text.clear()
    }


}