package com.example.a491bproject.adapters

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.a491bproject.R
import com.example.a491bproject.RecipeInstructions
import com.example.a491bproject.models.RecipeByIngredients

class RecipeByIngredientsAdapter(
    val context: Context,
    val recipeByIngredients: ArrayList<RecipeByIngredients.RecipeByIngredientsItem>
) :
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
            .inflate(R.layout.row_recipes_ingredients, viewGroup, false)
        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        // Allows us to click on the current card view
        viewHolder.itemView.setOnClickListener {
            // Testing if click listener is working
            Toast.makeText(context, "Test " + viewHolder.adapterPosition, Toast.LENGTH_SHORT).show()

            // Gets the id of the recipe you clicked on
            val recipeId = recipeByIngredients[position].id

            // Bundle the recipe id to pass onto the next activity
            val toPass = Bundle()
            toPass.putInt("recipeId", recipeId)

            // Declare new intent for the recipe instructions
            val intent = Intent(context, RecipeInstructions::class.java)
            // Set flag to enable a new activity to avoid app crashing
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            intent.putExtras(toPass)
            context.startActivity(intent)
        }
        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.tvRecipeIngredient.text = recipeByIngredients[position].title
        // TODO set image by calling the provided URL
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = recipeByIngredients.size

}