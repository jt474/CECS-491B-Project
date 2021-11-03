package com.example.a491bproject.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.a491bproject.R
import com.example.a491bproject.models.UserRecipesModel
import org.w3c.dom.Text

class UserRecipesAdapter(var recipes: MutableList<UserRecipesModel>):
    RecyclerView.Adapter<UserRecipesAdapter.UserRecipesViewHolder>() {

    inner class UserRecipesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserRecipesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.user_recipes_layout, parent, false) //attach to root must be false.
        return UserRecipesViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserRecipesViewHolder, position: Int) {
        holder.itemView.findViewById<TextView>(R.id.tvUserRecipeTitle).text = recipes[position].title
        holder.itemView.findViewById<CheckBox>(R.id.cbUserRecipes).isChecked = recipes[position].isChecked

    }

    override fun getItemCount(): Int {
        return recipes.size
    }

    fun deleteItem(position:Int){
        recipes.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position,recipes.size)
    }
}