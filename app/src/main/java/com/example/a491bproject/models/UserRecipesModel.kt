package com.example.a491bproject.models
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserRecipesModel(val authorID: String, val recipeID: String, val title:String, ):
    Parcelable {
    constructor() : this("default authorID", "default recipeID", "default title") {}
}
