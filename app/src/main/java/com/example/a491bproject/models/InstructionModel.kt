package com.example.a491bproject.models

data class InstructionModel(val number: String? = "0", val step: String? = "default instructions"){
    //number refers to the position of the instruction in the recipe.
    //step, taken from spoonacular api's documentation, is the description/text of the instruction.
}
