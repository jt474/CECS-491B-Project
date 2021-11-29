package com.example.a491bproject

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.a491bproject.models.Recipes
import com.squareup.picasso.Picasso
import java.lang.StringBuilder

class RecipesListAdapter(val context : Context, val recipesList : Recipes) :
    RecyclerView.Adapter<RecipesListAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvRecipeName: TextView = view.findViewById(R.id.tv_recipe_name)
        val tvRecipePrice: TextView = view.findViewById(R.id.tv_recipe_price)
        val imageRecipe: ImageView = view.findViewById(R.id.image_recipe)
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
        viewHolder.tvRecipeName.text = recipesList.results[position].title
        viewHolder.tvRecipePrice.text = "Calories: " + recipesList.results[position].nutrition.nutrients[0].amount + " " +
                recipesList.results[position].nutrition.nutrients[0].unit
        // insert image to be found online into image view
        // Reference: https://www.raywenderlich.com/3658341-picasso-tutorial-for-android-getting-started
        val picasso = Picasso.get()
        picasso.load(recipesList.results[position].image).into(viewHolder.imageRecipe)
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = recipesList.results.size

}