package com.example.a491bproject.interfaces

import com.example.a491bproject.models.UserRecipesModel

interface onClickUserRecipeListener {
    fun onSelectClick(model: UserRecipesModel)
    fun onEditClick(model:UserRecipesModel)
}