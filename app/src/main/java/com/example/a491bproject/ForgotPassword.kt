package com.example.a491bproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class ForgotPassword : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        val emailText =  findViewById<EditText>(R.id.Email)

        val submitBtn: Button = findViewById<Button>(R.id.submitBtn)

        submitBtn.setOnClickListener{
            when{
                TextUtils.isEmpty(emailText.text.toString()) -> {
                    Toast.makeText(this@ForgotPassword,
                        "Enter email",
                        Toast.LENGTH_SHORT

                    ).show()
                }
                else ->{
                    val email: String = emailText.text.toString()
                    FirebaseAuth.getInstance().sendPasswordResetEmail(email)
                        .addOnCompleteListener{task ->
                            if(task.isSuccessful){
                                Toast.makeText(this@ForgotPassword,
                                    "Password reset email sent",
                                    Toast.LENGTH_SHORT

                                ).show()
                                finish()
                            }
                            //Display error message to user
                            else{
                                Toast.makeText(this@ForgotPassword,
                                    task.exception!!.message.toString(),
                                    Toast.LENGTH_LONG

                                ).show()
                            }
                        }
                }
            }
        }

    }
}