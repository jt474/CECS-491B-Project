package com.example.a491bproject

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val registerBtn: Button = findViewById<Button>(R.id.register_button)
        val emailText = findViewById<EditText>(R.id.editTextEmailAddress)
        val passwordText = findViewById<EditText>(R.id.editTextPassword)

        //Navigates to Login Menu
        val login: TextView = findViewById<TextView>(R.id.loginScreen)
        login.setOnClickListener(){
            val loginIntent = Intent(this,LoginActivity::class.java)
            startActivity(loginIntent)
        }

        //When User clicks Register it should make an account
        registerBtn.setOnClickListener {
            //Checks if fields are empty
            when{
                TextUtils.isEmpty(emailText.text.toString()) -> {
                    Toast.makeText(this@RegisterActivity,
                        "Enter email",
                        Toast.LENGTH_SHORT

                            ).show()
                }
                TextUtils.isEmpty(passwordText.text.toString()) -> {
                    Toast.makeText(this@RegisterActivity,
                        "Enter password",
                        Toast.LENGTH_SHORT

                    ).show()
                }
                else -> {
                    val email: String = emailText.text.toString()
                    val password: String = passwordText.text.toString()

                    //Create firebase instance and create using email and password
                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password).addOnCompleteListener(
                        OnCompleteListener < AuthResult > { task ->

                            if (task.isSuccessful) {

                                //Firebase user registered
                                val firebaseUser: FirebaseUser = task.result!!.user!!
                                val user = Firebase.auth.currentUser

                                user!!.sendEmailVerification()
                                    .addOnCompleteListener { task ->
                                        if (task.isSuccessful) {
                                            Log.d(TAG, "Email sent.")
                                        }
                                    }

                                Toast.makeText(
                                    this@RegisterActivity,
                                    "You have Registered",
                                    Toast.LENGTH_SHORT
                                ).show()
                                //New user should be made at this point

                                //Navigates newly made account to main and clear previous intents
                                val registeredIntent = Intent(this@RegisterActivity, VerifyActivity::class.java)
                                registeredIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                //registeredIntent.putExtra("emailID", email)
                                startActivity(registeredIntent)
                                FirebaseAuth.getInstance().signOut()
                                finish()
                            }
                            //When Register fails print error
                            else {
                                Toast.makeText(
                                    this@RegisterActivity,
                                    task.exception!!.message.toString(),
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    )
                }
            }
        }
        // Naming for menu
        val actionBar = supportActionBar

        if (actionBar != null) {
            actionBar.title = "Register"
        }
    }
}