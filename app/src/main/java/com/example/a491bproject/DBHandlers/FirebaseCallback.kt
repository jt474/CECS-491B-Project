package com.example.a491bproject.DBHandlers

import com.example.a491bproject.models.UserRecipesModel

interface FirebaseCallback {
        fun onGetUserRecipeSuccess(list: MutableList<UserRecipesModel>)
        fun onFailure(error: String)
}