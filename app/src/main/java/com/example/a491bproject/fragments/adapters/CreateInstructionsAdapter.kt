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
import com.example.a491bproject.models.InstructionModel

class CreateInstructionsAdapter():
    RecyclerView.Adapter<CreateInstructionsAdapter.CreateInstructionsViewHolder>(){

    private var instructions = mutableListOf<InstructionModel>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CreateInstructionsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.create_instructions_layout,parent, false)
        return CreateInstructionsViewHolder(view)
    }

    override fun onBindViewHolder(holder: CreateInstructionsViewHolder, position: Int) {
        val model = instructions[position]
        val newStepNumber = "Step: ${model.getNumberPlusOne().toString()}"
        Log.d("CreateInstructions", "OnBindViewHolder newStepNumberMsg: $newStepNumber")
        holder.tvStepNumber.text = newStepNumber
        holder.tvInstructionText.text = model.step

        holder.ivInstructionDelete.setOnClickListener{
            val removed = instructions[position]
            val alert = AlertDialog.Builder(holder.context)
            alert.setTitle("Delete entry");
            alert.setMessage("Are you sure you want to delete? If not click anywhere else.\n\"${model.step}\"?");
            alert.setPositiveButton(android.R.string.ok, DialogInterface.OnClickListener() {
                    dialogInterface, i ->  deleteItem(position)
            })
            alert.show()
        }
    }

    override fun getItemCount(): Int {
        return instructions.size
    }

    fun submitInstruction(instructionText:String){
        val stepNumber = instructions.size.toLong()
        val model = InstructionModel(stepNumber, instructionText)
        instructions.add(model)
        Log.d("submitIngredients", "$instructions")
        notifyDataSetChanged()
    }

    private fun deleteItem(position:Int){
        instructions.removeAt(position)
        adjustStepNumbers(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position,instructions.size)
        Log.d("DeleteItem", "CreateInstructionsList now contains $instructions")
    }

    inner class CreateInstructionsViewHolder(view: View):RecyclerView.ViewHolder(view) {
        val tvStepNumber: TextView = view.findViewById(R.id.tvCreateRecipeInstructionStepNumber)
        val tvInstructionText: TextView = view.findViewById(R.id.tvCreateRecipeInstructionText)
        val ivInstructionDelete: ImageView = view.findViewById<ImageView>(R.id.ivCreateRecipeInstructionDelete)
        val context: android.content.Context = view.context
    }

    private fun adjustStepNumbers(position:Int){
        val listIterator = instructions.listIterator(position)
        while(listIterator.hasNext()){
            val model = listIterator.next()
            val newNumber = model.number!!.minus(1)
            val stepInstruction = model.step
            val newModel = InstructionModel(newNumber, stepInstruction)
            listIterator.set(newModel)
        }
    }
}