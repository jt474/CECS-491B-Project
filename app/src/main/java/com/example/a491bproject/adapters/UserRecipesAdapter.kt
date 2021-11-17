package com.example.a491bproject.adapters

import android.app.AlertDialog
import android.content.DialogInterface
import android.media.Image
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.a491bproject.R
import com.example.a491bproject.models.UserRecipesModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.core.Context
import com.google.firebase.ktx.Firebase
import org.w3c.dom.Text

class UserRecipesAdapter( val recipes: MutableList<UserRecipesModel>):
    RecyclerView.Adapter<UserRecipesAdapter.UserRecipesViewHolder>() {

    private val mAuth:FirebaseAuth by lazy{ FirebaseAuth.getInstance()}

    inner class UserRecipesViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val tvRecipeTitle:TextView = view.findViewById<TextView>(R.id.tvUserRecipeTitle)
        val ivEditUserRecipe:ImageView = view.findViewById<ImageView>(R.id.ivEditUserRecipe)
        val ivDeleteUserRecipe:ImageView = view.findViewById<ImageView>(R.id.ivDeleteUserRecipe)
        val context:android.content.Context= view.context


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserRecipesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.user_recipes_layout, parent, false) //attach to root must be false.
        return UserRecipesViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserRecipesViewHolder, position: Int) {
        val model = recipes[position]
        holder.tvRecipeTitle.text = model.title
        holder.ivDeleteUserRecipe.setOnClickListener{
            val deletedRecipe = recipes[position]
            val alert = AlertDialog.Builder(holder.context)
            alert.setTitle("Delete entry");
            alert.setMessage("Are you sure you want to delete? If not click anywhere else. \"$model.title\"?");
            alert.setPositiveButton(android.R.string.ok, DialogInterface.OnClickListener() {
                dialogInterface, i ->  deleteItem(position)
            })
            alert.show()
        }

    }

    override fun getItemCount(): Int {
        return recipes.size
    }

    private fun deleteItem(position:Int){
        val modelRecipeID = recipes[position].recipeID
        val dbRef = FirebaseDatabase.getInstance().reference
        val updateMap = mutableMapOf<String, Any?>(
            "Recipes/${modelRecipeID}" to null,
            "Instructions/${modelRecipeID}" to null,
            "Ingredients/${modelRecipeID}" to null
        )
        dbRef.updateChildren(updateMap) //double check if asynchronous

        recipes.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position,recipes.size)
        Log.d("DeleteItem", "List now contains $recipes")
    }

}