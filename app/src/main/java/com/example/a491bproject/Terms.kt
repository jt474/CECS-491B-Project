package com.example.a491bproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.widget.Button
import android.widget.TextView

class Terms : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_terms)

        val terms: TextView = findViewById<TextView>(R.id.terms)

        terms.movementMethod = ScrollingMovementMethod();

        // Naming for menu
        val actionBar = supportActionBar

        if (actionBar != null) {
            actionBar.title = "Terms and Conditions"
        }
    }
}