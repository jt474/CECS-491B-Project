package com.example.a491bproject.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.a491bproject.R
import com.example.a491bproject.models.UserRecipesModel

class UserRecipesAdapter(var recipes: List<UserRecipesModel>):
    RecyclerView.Adapter<UserRecipesAdapter.UserRecipesViewHolder>() {
    inner class UserRecipesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserRecipesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.user_recipes_layout, parent, false) //attach to root must be false.
        return UserRecipesViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserRecipesViewHolder, position: Int) {
        holder.itemView.apply{

        }
    }

    override fun getItemCount(): Int {
        return recipes.size
    }
}