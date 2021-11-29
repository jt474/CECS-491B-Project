package com.example.a491bproject.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.a491bproject.interfaces.UpdateRecipeViewModelListener
import com.example.a491bproject.models.IngredientModel
import com.example.a491bproject.models.InstructionModel


class UpdateRecipeViewModel(): ViewModel() {
    private var viewModelListener:UpdateRecipeViewModelListener? = null

    var authorID: String = ""
        set(value){
            field = value
            Log.d("UpdateViewModel","setAuthorID: $authorID")
            viewModelListener?.onChanged()
        }

    var recipeTitle: String = ""
        set(value){
            field = value.trim()
            Log.d("UpdateViewModel","setRecipeTitle: $recipeTitle")
            viewModelListener?.onChanged()
        }

    var description: String = ""
        set(value){
            field = value.trim()
            Log.d("UpdateViewModel","setDescription: $description")
            viewModelListener?.onChanged()
        }

    var instructions: MutableList<InstructionModel> = mutableListOf()
        set(value){
            field = value
            Log.d("UpdateViewModel","setInstructions: $instructions")
            viewModelListener?.onChanged()
        }

    var ingredients: MutableList<IngredientModel> = mutableListOf()
        set(value){
            field = value
            Log.d("UpdateViewModel","setIngredients: $ingredients")
            viewModelListener?.onChanged()
        }

    fun isNotMissingInfo():Boolean{
        return recipeTitle.isNotEmpty() && description.isNotEmpty() && instructions.isNotEmpty() && ingredients.isNotEmpty()
    }

    fun setViewModelListener(viewModelListener: UpdateRecipeViewModelListener){
        this.viewModelListener = viewModelListener
    }
}