package com.example.a491bproject.fragments.adapters

import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.a491bproject.R
import com.example.a491bproject.models.InstructionModel

class RecipeInstructionsAdapter():
    RecyclerView.Adapter<RecipeInstructionsAdapter.RecipeInstructionsViewHolder>() {

    private var instructions = mutableListOf<InstructionModel>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecipeInstructionsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recipe_instructions_layout, parent, false) //attach to root must be false.
        return RecipeInstructionsViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecipeInstructionsViewHolder, position: Int) {
        val model = instructions[position]
        val stepNumberPlusOne = model.number!!.toInt() + 1
        val newStepNumber = "Step: $stepNumberPlusOne"
        Log.d("InstructionsFragment", "OnBindViewHolder newStepNumberMsg: $newStepNumber")
        holder.tvStepNumber.text = newStepNumber
        holder.tvInstructionText.text = model.step
    }

    override fun getItemCount(): Int {
        return instructions.size
    }

    fun submitInstructions(list:MutableList<InstructionModel>){
        instructions = list
        Log.d("submitInstructions","$instructions")
        notifyDataSetChanged()
    }

    inner class RecipeInstructionsViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val tvStepNumber:TextView = view.findViewById<TextView>(R.id.tvStepNumber)
        val tvInstructionText: TextView = view.findViewById<TextView>(R.id.tvInstructionText)
        val context:android.content.Context = view.context
    }
}