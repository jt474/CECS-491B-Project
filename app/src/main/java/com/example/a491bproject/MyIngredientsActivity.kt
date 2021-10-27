package com.example.a491bproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MyIngredientsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_ingredients)
        back()
    }

    private fun back() {
        findViewById<Button>(R.id.button_back).setOnClickListener { this.finish() }
    }
}