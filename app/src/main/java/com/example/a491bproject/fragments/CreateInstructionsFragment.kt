package com.example.a491bproject.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.a491bproject.R
import com.example.a491bproject.fragments.adapters.CreateInstructionsAdapter
import com.example.a491bproject.models.InstructionModel

class CreateInstructionsFragment : Fragment() {
    private lateinit var viewCreateInstructions:View
    private lateinit var etCreateRecipeInstruction:EditText
    private lateinit var btnAddStep:Button
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CreateInstructionsAdapter
    
    private var stepInstructionEntered:Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewCreateInstructions =  inflater.inflate(R.layout.fragment_create_instructions, container, false)
        etCreateRecipeInstruction = viewCreateInstructions.findViewById(R.id.etCreateRecipeInstruction)
        btnAddStep = viewCreateInstructions.findViewById(R.id.btnCreateRecipeAddStep)
        adapter = CreateInstructionsAdapter()
        initializeRecyclerView(adapter, viewCreateInstructions)

        return viewCreateInstructions
    }

    private fun initializeRecyclerView(adapter: CreateInstructionsAdapter, view: View){
        recyclerView = view.findViewById(R.id.rvCreateRecipeIngredients)
        recyclerView.layoutManager = LinearLayoutManager(this.context)
        recyclerView.adapter = adapter
    }

    private fun addTextChangedListeners(){
        etCreateRecipeInstruction.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(p0.toString().trim().isEmpty()){
                    stepInstructionEntered = false
                    tryEnableButton()
                } else {
                    stepInstructionEntered = true
                    tryEnableButton()
                }
            }

            override fun afterTextChanged(p0: Editable?) {
                TODO("Not yet implemented")
            }

        })
    }

    private fun tryEnableButton(){
        Log.d("TryEnableButton","$stepInstructionEntered")
        btnAddStep.isEnabled = stepInstructionEntered
    }

    private fun addOnClickListeners(){
        btnAddStep.setOnClickListener{
            val stepInstruction = etCreateRecipeInstruction.text.toString()
            Log.d("OnClickListener","CreateInstructionsFragment: Model contains: ${instruction.toString()}")
            adapter.submitInstruction(stepInstruction)
            clearEditTexts()
        }
    }

    private fun clearEditTexts(){
        etCreateRecipeInstruction.text.clear()
    }


}