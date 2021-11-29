package com.example.a491bproject.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.a491bproject.interfaces.CreateRecipeViewModelListener
import com.example.a491bproject.models.*
import com.google.firebase.auth.FirebaseAuth
import java.text.SimpleDateFormat
import java.util.*
//https://kotlinlang.org/docs/coding-conventions.html#names-for-backing-properties for refactoring
class CreateRecipeViewModel:ViewModel() {
    private var recipeTitle: String = ""
    private var description: String = ""
    private var instructions: MutableList<InstructionModel> = mutableListOf<InstructionModel>()
    private var ingredients: MutableList<IngredientModel> = mutableListOf<IngredientModel>()
    private lateinit var viewModelListener: CreateRecipeViewModelListener

    fun getRecipeTitle():String{
        return recipeTitle
    }

    fun getDescription():String{
        return description
    }

    fun getInstructions():MutableList<InstructionModel>{
        return instructions
    }

    fun getIngredients():MutableList<IngredientModel>{
        return ingredients
    }

    fun setViewModelListener(listener:CreateRecipeViewModelListener){
        this.viewModelListener = listener
    }

    fun setTitle(newValue:String, calledFrom:String){
        Log.d("setTitle","$calledFrom:\n NewTitle: $newValue, OldTitle: $recipeTitle\n")
        this.recipeTitle = newValue
        Log.d("setTitle","CreateViewModel now has $recipeTitle")
        viewModelListener.onChanged()
    }

    fun setDescription(newValue:String, calledFrom:String){
        Log.d("setDescription","$calledFrom:\n NewDescription: $newValue, OldDescription: $description\n")
        this.description = newValue
        Log.d("setDescription","CreateViewModel now has $description")
        viewModelListener.onChanged()
    }

    fun submitInstructions( newValues:MutableList<InstructionModel>, calledFrom:String){
        this.instructions = newValues
        Log.d("submitInstructions","CreateViewModel now has $instructions")
        viewModelListener.onChanged()
    }

    fun submitIngredients( newValues:MutableList<IngredientModel>, calledFrom:String){
        this.ingredients = newValues
        Log.d("submitIngredients","CreateViewModel now has $ingredients")
        viewModelListener.onChanged()
    }

    fun isNotMissingInfo():Boolean{
        return recipeTitle.isNotEmpty() && description.isNotEmpty() && instructions.isNotEmpty() && ingredients.isNotEmpty()
    }



}