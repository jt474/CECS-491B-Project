package com.example.a491bproject.models

class RecipeByIngredients : ArrayList<RecipeByIngredients.RecipeByIngredientsItem>(){
    data class RecipeByIngredientsItem(
        val id: Int,
        val image: String,
        val imageType: String,
        val likes: Int,
        val missedIngredientCount: Int,
        val missedIngredients: List<MissedIngredient>,
        val title: String,
        val unusedIngredients: List<Any>,
        val usedIngredientCount: Int,
        val usedIngredients: List<UsedIngredient>
    ) {
        data class MissedIngredient(
            val aisle: String,
            val amount: Double,
            val id: Int,
            val image: String,
            val meta: List<Any>,
            val metaInformation: List<Any>,
            val name: String,
            val original: String,
            val originalName: String,
            val originalString: String,
            val unit: String,
            val unitLong: String,
            val unitShort: String
        )
    
        data class UsedIngredient(
            val aisle: String,
            val amount: Double,
            val id: Int,
            val image: String,
            val meta: List<String>,
            val metaInformation: List<String>,
            val name: String,
            val original: String,
            val originalName: String,
            val originalString: String,
            val unit: String,
            val unitLong: String,
            val unitShort: String
        )
    }
}