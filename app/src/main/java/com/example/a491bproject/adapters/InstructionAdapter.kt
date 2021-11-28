package com.example.a491bproject.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.a491bproject.R
import com.example.a491bproject.models.Instructions

class InstructionAdapter(
    val context: Context,
    val instructions: ArrayList<Instructions.InstructionsItem>
) :
    RecyclerView.Adapter<InstructionAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvInstructionStep: TextView = view.findViewById(R.id.tv_instruction_number)
        val tvInstructionInfo: TextView = view.findViewById(R.id.tv_instruction_info)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.row_recipes_instructions, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.tvInstructionStep.text =
            "Step: " + instructions[0].steps[position].number.toString()
        viewHolder.tvInstructionInfo.text = instructions[0].steps[position].step
    }

    override fun getItemCount() = instructions[0].steps.size
}