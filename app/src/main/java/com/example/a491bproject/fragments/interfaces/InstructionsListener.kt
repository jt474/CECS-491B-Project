package com.example.a491bproject.fragments.interfaces

import com.example.a491bproject.models.InstructionModel

interface InstructionsListener {
    fun onInstructionsChanged(list:MutableList<InstructionModel>)
}