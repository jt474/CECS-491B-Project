package com.example.a491bproject.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.a491bproject.R
import com.example.a491bproject.models.InstructionModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import org.w3c.dom.Text

class RecipeInstructionsAdapter(val instructions: MutableList<InstructionModel>):
    RecyclerView.Adapter<RecipeInstructionsAdapter.RecipeInstructionsViewHolder>() {

    private val mAuth:FirebaseAuth by lazy{FirebaseAuth.getInstance()}

    inner class RecipeInstructionsViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val tvStepNumber:TextView = view.findViewById<TextView>(R.id.tvStepNumber)
        val tvInstructionText: TextView = view.findViewById<TextView>(R.id.tvInstructionText)
        val context:android.content.Context = view.context
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecipeInstructionsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recipe_instructions_layout, parent, false) //attach to root must be false.
        return RecipeInstructionsViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecipeInstructionsViewHolder, position: Int) {
        val model = instructions[position]
        val newStepNumber = "${R.string.StepNumberHeader}${model.number}"
        holder.tvStepNumber.text = newStepNumber
        holder.tvInstructionText.text = model.step
    }

    override fun getItemCount(): Int {
        return instructions.size
    }
}