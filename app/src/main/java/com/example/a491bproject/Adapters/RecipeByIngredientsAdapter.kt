package com.example.a491bproject.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.a491bproject.R
import com.example.a491bproject.models.RecipeByIngredients

class RecipeByIngredientsAdapter (val context : Context, val recipeByIngredients: RecipeByIngredients) :
    RecyclerView.Adapter<RecipeByIngredientsAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvRecipeIngredient: TextView = view.findViewById(R.id.title)
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(context)
            .inflate(R.layout.row_recipes, viewGroup, false)
        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.tvRecipeIngredient.text = recipeByIngredients[position].title
        // TODO set image by calling the provided URL
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = recipeByIngredients.size

}