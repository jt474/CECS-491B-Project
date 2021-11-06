package com.example.a491bproject.models

data class Recipes(
    val calories: Int,
    val carbs: String,
    val fat: String,
    val id: Int,
    val image: String,
    val imageType: String,
    val protein: String,
    val title: String
)

//    (
//    val number: Int,
//    val offset: Int,
//    val results: List<Result>,
//    val totalResults: Int
//) {
//    data class Result(
//        val id: Int,
//        val image: String,
//        val imageType: String,
//        val nutrition: Nutrition,
//        val title: String
//    ) {
//        data class Nutrition(
//            val nutrients: List<Nutrient>
//        ) {
//            data class Nutrient(
//                val amount: Double,
//                val name: String,
//                val title: String,
//                val unit: String
//            )
//        }
//    }
//}