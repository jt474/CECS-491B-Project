package com.example.a491bproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Transitions from Main to Ingredients
        val ingredientsListBtn: Button = findViewById<Button>(R.id.ingredientsListBtn)
        ingredientsListBtn.setOnClickListener() {
            val ingredientsIntent = Intent(this, IngredientsActivity::class.java)
            startActivity(ingredientsIntent)

        }

        //Signout
        val logoutBtn: Button = findViewById<Button>(R.id.logoutBtn)
        logoutBtn.setOnClickListener{
            FirebaseAuth.getInstance().signOut()

            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

        // Naming for menu
        val actionBar = supportActionBar

        if(actionBar != null){
            actionBar.title = "Main Menu"
        }
    }
}