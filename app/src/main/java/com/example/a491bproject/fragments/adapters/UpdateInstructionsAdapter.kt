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
import com.example.a491bproject.fragments.interfaces.InstructionsListener
import com.example.a491bproject.models.InstructionModel

class UpdateInstructionsAdapter (listener: InstructionsListener):
    RecyclerView.Adapter<UpdateInstructionsAdapter.UpdateInstructionsViewHolder>(){
    private var instructions = mutableListOf<InstructionModel>()
    private var instructionsListener = listener
    private val logTag = "UpdateInstructionsAdpt"

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UpdateInstructionsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.entered_instructions_layout,parent, false)
        return UpdateInstructionsViewHolder(view)
    }

    override fun onBindViewHolder(holder: UpdateInstructionsAdapter.UpdateInstructionsViewHolder, position: Int) {
        val model = instructions[position]
        val stepNumberPlusOne = model.number!!.toInt() + 1
        val newStepNumber = "Step: $stepNumberPlusOne"
        Log.d(logTag, "OnBindViewHolder newStepNumberMsg: $newStepNumber")
        holder.tvStepNumber.text = newStepNumber
        holder.tvInstructionText.text = model.step

        holder.ivInstructionDelete.setOnClickListener{
            val removed = instructions[position]
            val alert = AlertDialog.Builder(holder.context)
            alert.setTitle("Delete entry");
            alert.setMessage("Are you sure you want to delete? If not click anywhere else.\n\"${removed.step}\"?");
            alert.setPositiveButton(android.R.string.ok, DialogInterface.OnClickListener() {
                    dialogInterface, i ->  deleteItem(position)
            })
            alert.show()
        }
    }

    override fun getItemCount(): Int {
        return instructions.size
    }

    fun setInstructionsListener(listener: InstructionsListener){
        this.instructionsListener = listener
    }

    fun submitInstruction(instructionText:String){
        val stepNumber = instructions.size.toLong()
        val model = InstructionModel(stepNumber, instructionText)
        instructions.add(model)
        Log.d(logTag, "Submitted Instructions: $instructions")
        notifyDataSetChanged()
        if(instructionsListener != null){
            instructionsListener.onInstructionsChanged(this.instructions)
        }
    }

    fun submitInstructions(list:MutableList<InstructionModel>){
        instructions = list
        Log.d(logTag, "Instructions = $instructions")
        notifyDataSetChanged()
        if(instructionsListener != null){
            instructionsListener.onInstructionsChanged(this.instructions)
        }
    }

    private fun deleteItem(position:Int){
        instructions.removeAt(position)
        adjustStepNumbers(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position,instructions.size)
        if(instructionsListener != null){
            instructionsListener.onInstructionsChanged(this.instructions)
        }
        Log.d(logTag, "Deleted an item. UpdateInstructionsList now contains $instructions")
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

    inner class UpdateInstructionsViewHolder(view: View):RecyclerView.ViewHolder(view){
        val tvStepNumber: TextView = view.findViewById(R.id.tvEnteredRecipeInstructionStepNumber)
        val tvInstructionText: TextView = view.findViewById(R.id.tvEnteredRecipeInstructionText)
        val ivInstructionDelete: ImageView = view.findViewById(R.id.ivEnteredRecipeInstructionDelete)
        val context: android.content.Context = view.context
    }
}