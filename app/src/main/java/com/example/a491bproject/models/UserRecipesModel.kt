package com.example.a491bproject.models

data class UserRecipesModel(val title:String, var isChecked:Boolean){
    constructor() : this("", false) {}
}
