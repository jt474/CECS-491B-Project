package com.example.a491bproject.fragments.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.a491bproject.R
import com.example.a491bproject.models.IngredientModel

class RecipeIngredientsAdapter ():
    RecyclerView.Adapter<RecipeIngredientsAdapter.RecipeIngredientsViewHolder>(){

    private var ingredients = mutableListOf<IngredientModel>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecipeIngredientsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recipe_ingredients_layout, parent, false) //attach to root must be false.
        return RecipeIngredientsViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: RecipeIngredientsViewHolder,
        position: Int
    ) {
        val model = ingredients[position]
        val newAmountUnit = "${model.amount} ${model.unit}"

        holder.tvIngredientName.text = model.name
        holder.tvAmountUnit.text = newAmountUnit
    }

    override fun getItemCount(): Int {
        return ingredients.size
    }

    fun submitIngredients(list:MutableList<IngredientModel>){
        ingredients = list
        Log.d("submitIngredients", "$ingredients")
        notifyDataSetChanged()
    }

    inner class RecipeIngredientsViewHolder(view: View):RecyclerView.ViewHolder(view) {
        val tvIngredientName: TextView = view.findViewById(R.id.tvRecipeIngredientName)
        val tvAmountUnit: TextView = view.findViewById(R.id.tvAmountUnit)
        val context: android.content.Context = view.context
    }

}