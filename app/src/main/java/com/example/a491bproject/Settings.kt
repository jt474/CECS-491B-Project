package com.example.a491bproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Settings : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        //Navigate to Profile
        val accountBtn: Button = findViewById<Button>(R.id.account)
        accountBtn.setOnClickListener{
            val accountIntent = Intent(this, ProfileActivity::class.java)
            startActivity(accountIntent)
        }

        //Navigate to Terms
        val termBtn: Button = findViewById<Button>(R.id.terms)
        termBtn.setOnClickListener{
            val termsIntent = Intent(this, Terms::class.java)
            startActivity(termsIntent)
        }

        //Contact
        val contactBtn: Button = findViewById<Button>(R.id.contact)
        contactBtn.setOnClickListener{
            val contactIntent = Intent(this, Contact::class.java)
            startActivity(contactIntent)
        }
    }
}