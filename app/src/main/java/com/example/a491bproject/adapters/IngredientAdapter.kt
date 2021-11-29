package com.example.a491bproject.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.a491bproject.R
import com.example.a491bproject.models.IngredientInfo

class IngredientAdapter (val context : Context, val ingredients: IngredientInfo) :
    RecyclerView.Adapter<IngredientAdapter.ViewHolder>(){

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvNutrientName: TextView = view.findViewById(R.id.tv_nutrient_name)
        val tvNutrientAmount: TextView = view.findViewById(R.id.tv_nutrient_amount)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(context)
            .inflate(R.layout.row_ingredients_info, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.tvNutrientName.text = ingredients.nutrition.nutrients[position].name
        viewHolder.tvNutrientAmount.text = ingredients.nutrition.nutrients[position].amount.toString() + ingredients.nutrition.nutrients[position].unit
    }

    override fun getItemCount() = ingredients.nutrition.nutrients.size

}