package com.example.a491bproject.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.a491bproject.models.*
import com.google.firebase.auth.FirebaseAuth
import java.text.SimpleDateFormat
import java.util.*

class CreateRecipeViewModel:ViewModel() {
    private var recipeID: String = "Default RecipeID"
    private var authorID: String ="Default AuthorID"
    private var dateUpdated: String = "Default Date"
    private var recipeTitle: String = "Default Title"
    private var description: String = ""
    private var aboutModel: RecipeAboutModel = RecipeAboutModel()
    private var instructions: MutableList<InstructionModel> = mutableListOf<InstructionModel>()
    private var ingredients: MutableList<IngredientModel> = mutableListOf<IngredientModel>()

    public fun setAuthorID(calledFrom:String){
        val newValue = getCurrentUserUID()
        Log.d("setAuthorID","$calledFrom:\n NewAuthorID: $newValue, OldAuthorID: $authorID\n")
        this.authorID = newValue
        Log.d("setAuthorID","ViewModel now has $authorID")
    }

    public fun setDate(calledFrom:String){
        val newValue = getCurrentDate()
        Log.d("setDate","$calledFrom:\n NewDate: $newValue, OldDate: $dateUpdated\n")
        this.dateUpdated = newValue
        Log.d("setDate","ViewModel now has $dateUpdated")
    }

    public fun setTitle(newValue:String, calledFrom:String){
        Log.d("setTitle","$calledFrom:\n NewTitle: $newValue, OldTitle: $recipeTitle\n")
        this.recipeTitle = newValue
        Log.d("setTitle","ViewModel now has $recipeTitle")
    }

    public fun setDescription(newValue:String, calledFrom:String){
        Log.d("setDescription","$calledFrom:\n NewDescription: $newValue, OldDescription: $description\n")
        this.description = newValue
        Log.d("setDescription","ViewModel now has $description")
    }

    public fun submitInstructions( newValues:MutableList<InstructionModel>, calledFrom:String){
        Log.d("submitInstructions","$calledFrom:\n NewInstructions: ${newValues.toString()}, OldInstructions: ${instructions.toString()}\n")
        this.instructions = newValues
        Log.d("","ViewModel now has ${instructions.toString()}")
    }

    public fun submitIngredients( newValues:MutableList<IngredientModel>, calledFrom:String){
        Log.d("submitInstructions","$calledFrom:\n NewIngredients: ${newValues.toString()}, OldIngredients: ${ingredients.toString()}\n")
        this.ingredients = newValues
        Log.d("","ViewModel now has ${ingredients.toString()}")
    }

    private fun getCurrentUserUID():String {
        val authorID = FirebaseAuth.getInstance().currentUser!!.uid
        Log.d("CreateViewModel", "AuthorID set to $authorID")
        return authorID
    }

    private fun getCurrentDate(): String{
        val sdf = SimpleDateFormat("MM/dd/yyyy")
        val lastUpdatedStr = sdf.format(Date())
        Log.d("getCurrentDate", "ViewModel.dateUpdated changed to $lastUpdatedStr")
        return lastUpdatedStr
    }

}