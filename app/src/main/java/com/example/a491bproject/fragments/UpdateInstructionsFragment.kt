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
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.a491bproject.R
import com.example.a491bproject.fragments.adapters.UpdateInstructionsAdapter
import com.example.a491bproject.fragments.interfaces.InstructionsListener
import com.example.a491bproject.models.InstructionModel
import com.example.a491bproject.viewmodels.UpdateRecipeViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.getValue

/**
 * A simple [Fragment] subclass.
 */
class UpdateInstructionsFragment : Fragment(), InstructionsListener {
    private lateinit var viewUpdateInstructions:View
    private lateinit var etUpdateRecipeInstruction:EditText
    private lateinit var btnAddStep:Button
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: UpdateInstructionsAdapter

    private val viewModel: UpdateRecipeViewModel by activityViewModels()
    private var stepInstructionEntered:Boolean = false
    private var instructionsInitialized:Boolean = false
    private val logTag = "UpdateInstructions"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewUpdateInstructions =  inflater.inflate(R.layout.fragment_update_instructions, container, false)
        etUpdateRecipeInstruction = viewUpdateInstructions.findViewById(R.id.etUpdateRecipeInstruction)
        btnAddStep = viewUpdateInstructions.findViewById(R.id.btnUpdateRecipeAddStep)
        btnAddStep.isEnabled = false

        initializeAdapter()
        initializeRecyclerView(adapter, viewUpdateInstructions)

        val recipeID = arguments?.getString(getString(R.string.RecipeID))
        if (recipeID != null){
            populateRecyclerView(recipeID)
        }

        addTextChangedListeners()
        addOnClickListeners()

        return viewUpdateInstructions
    }

    override fun onInstructionsChanged(list: MutableList<InstructionModel>) {
        viewModel.instructions = list
    }

    /**
     * Set up functions
     */
    private fun initializeAdapter(){
        adapter= UpdateInstructionsAdapter(this)
    }

    private fun initializeRecyclerView(adapter:UpdateInstructionsAdapter, view: View){
        recyclerView = view.findViewById(R.id.rvUpdateRecipeInstructions)
        recyclerView.layoutManager = LinearLayoutManager(this.context)
        recyclerView.adapter = adapter
    }

    private fun populateRecyclerView(recipeID:String){
        val dbRef = FirebaseDatabase.getInstance().reference
        val query = dbRef.child("Instructions").child(recipeID).addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if(!instructionsInitialized){//We don't want instructions to be added/reset if someone makes changes from another isntance.
                    val instructions = mutableListOf<InstructionModel>()
                    for(postSnapshot in snapshot.children){
                        val model = postSnapshot.getValue<InstructionModel>()
                        if(model!=null){
                            Log.d(logTag, "Reference: ${postSnapshot.ref}")
                            instructions.add(model)
                        }
                    }
                    adapter.submitInstructions(instructions)
                    instructionsInitialized = true
                }
            }

            override fun onCancelled(error: DatabaseError) {
               Log.d(logTag, "An error occurred while trying to retrieve data about recipeID: $recipeID")
            }

        })
    }


    private fun addTextChangedListeners(){
        etUpdateRecipeInstruction.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) { }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    stepInstructionEntered = p0.toString().trim().isNotEmpty()
                    tryEnableButton()
            }
            override fun afterTextChanged(p0: Editable?) { }
        })
    }

    private fun addOnClickListeners(){
        btnAddStep.setOnClickListener{
            val stepInstruction = etUpdateRecipeInstruction.text.toString().trim()
            Log.d(logTag,"addOnClick Model contains: ${stepInstruction.toString()}")
            adapter.submitInstruction(stepInstruction)
            clearEditTexts()
        }
    }

    private fun clearEditTexts(){
        etUpdateRecipeInstruction.text.clear()
    }

    private fun tryEnableButton(){
        Log.d(logTag,"$stepInstructionEntered")
        btnAddStep.isEnabled = stepInstructionEntered
    }

}