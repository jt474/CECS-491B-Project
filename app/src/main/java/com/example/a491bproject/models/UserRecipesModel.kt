package com.example.a491bproject.models

data class UserRecipesModel(val authorID: String, val recipeID: String, val title:String, ){
    constructor() : this("default authorID", "default recipeID", "default title") {}
}
