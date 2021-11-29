package com.example.a491bproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.firebase.auth.FirebaseAuth

class VerifyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verify)

        val backLoginBtn: Button = findViewById<Button>(R.id.backLogin)
        backLoginBtn.setOnClickListener() {
            FirebaseAuth.getInstance().signOut()
            val loginIntent3 = Intent(this, LoginActivity::class.java)
            startActivity(loginIntent3)
        }
        // Naming for menu
        val actionBar = supportActionBar

        if (actionBar != null) {
            actionBar.title = "Verify Email"
        }
    }
}