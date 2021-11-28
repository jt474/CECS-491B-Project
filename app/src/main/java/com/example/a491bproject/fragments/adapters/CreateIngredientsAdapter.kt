package com.example.a491bproject.fragments.adapters
import android.app.AlertDialog
import android.content.DialogInterface
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.a491bproject.R
import com.example.a491bproject.models.IngredientModel

class CreateIngredientsAdapter ():
    RecyclerView.Adapter<CreateIngredientsAdapter.CreateIngredientsViewHolder>(){

    private var ingredients = mutableListOf<IngredientModel>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CreateIngredientsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.create_ingredients_layout, parent, false) //attach to root must be false.
        return CreateIngredientsViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: CreateIngredientsViewHolder,
        position: Int
    ) {
        val model = ingredients[position]
        val newAmountUnit = "${model.amount} ${model.unit}"

        holder.tvIngredientName.text = model.name
        holder.tvAmountUnit.text = newAmountUnit

        holder.ivIngredientDelete.setOnClickListener{
            val removed = ingredients[position]
            val alert = AlertDialog.Builder(holder.context)
            alert.setTitle("Delete entry");
            alert.setMessage("Are you sure you want to delete? If not click anywhere else. \"${model.name}\"?");
            alert.setPositiveButton(android.R.string.ok, DialogInterface.OnClickListener() {
                    dialogInterface, i ->  deleteItem(position)
            })
            alert.show()
        }
    }

    override fun getItemCount(): Int {
        return ingredients.size
    }

    fun submitIngredients(list:MutableList<IngredientModel>){
        ingredients = list
        Log.d("submitIngredients", "$ingredients")
        notifyDataSetChanged()
    }

    private fun deleteItem(position:Int){
        ingredients.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position,ingredients.size)
        Log.d("DeleteItem", "List now contains $ingredients")
    }

    inner class CreateIngredientsViewHolder(view: View):RecyclerView.ViewHolder(view) {
        val tvIngredientName: TextView = view.findViewById(R.id.tvCreateRecipeIngredientName)
        val tvAmountUnit: TextView = view.findViewById(R.id.tvCreateRecipeIngredientAmountUnit)
        val ivIngredientDelete: ImageView = view.findViewById<ImageView>(R.id.ivCreateRecipeIngredientDelete)
        val context: android.content.Context = view.context
    }

}