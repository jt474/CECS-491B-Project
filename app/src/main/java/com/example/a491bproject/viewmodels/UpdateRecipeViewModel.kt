package com.example.a491bproject.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.a491bproject.interfaces.UpdateRecipeViewModelListener
import com.example.a491bproject.models.IngredientModel
import com.example.a491bproject.models.InstructionModel

class UpdateRecipeViewModel: ViewModel() {

    private var recipeTitle: String = ""
    private var description: String = ""
    private var instructions: MutableList<InstructionModel> = mutableListOf<InstructionModel>()
    private var ingredients: MutableList<IngredientModel> = mutableListOf<IngredientModel>()
    private lateinit var viewModelListener: UpdateRecipeViewModelListener

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

    fun setViewModelListener(listener: UpdateRecipeViewModelListener){
        this.viewModelListener = listener
    }

    fun setTitle(newValue:String, calledFrom:String){
        Log.d("setTitle","$calledFrom:\n NewTitle: $newValue, OldTitle: $recipeTitle\n")
        this.recipeTitle = newValue
        Log.d("setTitle","UpdateViewModel now has $recipeTitle")
        viewModelListener.onChanged()
    }

    fun setDescription(newValue:String, calledFrom:String){
        Log.d("setDescription","$calledFrom:\n NewDescription: $newValue, OldDescription: $description\n")
        this.description = newValue
        Log.d("setDescription","UpdateViewModel now has $description")
        viewModelListener.onChanged()
    }

    fun submitInstructions(newValues:MutableList<InstructionModel>, calledFrom:String){
        this.instructions = newValues
        Log.d("submitInstructions","UpdateViewModel now has $instructions")
        viewModelListener.onChanged()
    }

    fun submitIngredients(newValues:MutableList<IngredientModel>, calledFrom:String){
        this.ingredients = newValues
        Log.d("submitIngredients","UpdateViewModel now has $ingredients")
        viewModelListener.onChanged()
    }

    fun isNotMissingInfo():Boolean{
        return recipeTitle.isNotEmpty() && description.isNotEmpty() && instructions.isNotEmpty() && ingredients.isNotEmpty()
    }
}