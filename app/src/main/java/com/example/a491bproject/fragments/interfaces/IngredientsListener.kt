package com.example.a491bproject.fragments.interfaces

import com.example.a491bproject.models.IngredientModel

interface IngredientsListener {
    fun onIngredientsChanged(list:MutableList<IngredientModel>)
}