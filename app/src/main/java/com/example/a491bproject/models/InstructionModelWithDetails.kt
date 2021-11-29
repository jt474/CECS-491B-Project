package com.example.a491bproject.models

data class InstructionModelWithDetails(val number: String, val step: String, val ingredients: List<String>, val equipment: List<String> ){
    //number refers to the position of the instruction in the recipe.
    //step, taken from spoonacular api's documentation, is the description/text of the instruction.
    //ingredients is a list of names:string
    //equipment is a list of names:String of relevant equipment.
}
