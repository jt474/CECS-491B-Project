package com.example.a491bproject.viewmodels

import androidx.lifecycle.ViewModel
import com.example.a491bproject.models.*

class CreateRecipeViewModel:ViewModel() {
    public var aboutModel: RecipeAboutModel = RecipeAboutModel()
    public val instructions: MutableList<InstructionModel> by lazy { mutableListOf<InstructionModel>() }
    public val ingredients: MutableList<IngredientModel> by lazy{ mutableListOf<IngredientModel>()}
    public var authorID: String ="Default AuthorID"
    public var dateUpdated: String = "Default Date"
    public var recipeID: String = "Default RecipeID"
    public var description: String = ""
    public var recipeTitle: String = "Default Title"


}