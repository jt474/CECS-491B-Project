package com.example.a491bproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val registerBtn: Button = findViewById<Button>(R.id.register_button)
        val emailText = findViewById<EditText>(R.id.editTextEmailAddress)
        val passwordText = findViewById<EditText>(R.id.editTextPassword)

        //Navigates to Login Menu
        val register: Button = findViewById<Button>(R.id.loginRegisterBtn)
        register.setOnClickListener(){
            val loginIntent = Intent(this,LoginActivity::class.java)
            startActivity(loginIntent)
        }

        //When User clicks Register it should make an account
        registerBtn.setOnClickListener {
            val email: String = emailText.text.toString()
            val password: String = passwordText.text.toString()

            //Create firebase instance and create using email and password
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password).addOnCompleteListener(
                OnCompleteListener < AuthResult > { task ->

                    if (task.isSuccessful) {

                        //Firebase user registered
                        val firebaseUser: FirebaseUser = task.result!!.user!!

                        Toast.makeText(this@RegisterActivity,
                            "You have Registered",
                        Toast.LENGTH_SHORT
                        ).show()

                        //New user should be made at this point

                    }
                }
            )
        }
    }
}