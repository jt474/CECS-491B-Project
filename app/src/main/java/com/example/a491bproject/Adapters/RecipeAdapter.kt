package com.example.a491bproject.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.a491bproject.R
import com.example.a491bproject.models.Recipes

class RecipeAdapter (val context: Context, val recipe: List<Recipes>): RecyclerView.Adapter<RecipeAdapter.ViewHolder>() {
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var calories: TextView
        var carbs: TextView
        var fat: TextView
        var protein: TextView
        var title: TextView

        init {
            calories = itemView.findViewById<TextView>(R.id.calories)
            carbs = itemView.findViewById<TextView>(R.id.carbs)
            fat = itemView.findViewById<TextView>(R.id.fat)
            protein = itemView.findViewById<TextView>(R.id.protein)
            title = itemView.findViewById<TextView>(R.id.title)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.row_items, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.calories.text = recipe[position].calories.toString()
        holder.carbs.text = recipe[position].carbs
        holder.fat.text = recipe[position].fat
        holder.protein.text = recipe[position].protein
        holder.title.text = recipe[position].title
    }

    override fun getItemCount(): Int {
        return recipe.size
    }
}