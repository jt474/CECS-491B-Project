package com.example.a491bproject.Adapters

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
        val tvNutrientUnit: TextView = view.findViewById(R.id.tv_nutrient_unit)
        val tvNutrientPercent: TextView = view.findViewById(R.id.tv_nutrient_percent)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(context)
            .inflate(R.layout.row_ingredients_info, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.tvNutrientName.text = ingredients.nutrition.nutrients[position].name
        viewHolder.tvNutrientAmount.text = ingredients.nutrition.nutrients[position].name.toString()
        viewHolder.tvNutrientUnit.text = ingredients.nutrition.nutrients[position].name
        viewHolder.tvNutrientPercent.text = ingredients.nutrition.nutrients[position].name.toString()
    }

    override fun getItemCount() = ingredients.nutrition.nutrients.size

}