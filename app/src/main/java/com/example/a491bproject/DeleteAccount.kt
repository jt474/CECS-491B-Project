package com.example.a491bproject

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth

class DeleteAccount : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete_account)

        var auth: FirebaseAuth = FirebaseAuth.getInstance()
        val currentPass = findViewById<EditText>(R.id.currentPassword)
        val confirmDelete: Button = findViewById<Button>(R.id.confirmDeleteBtn)

        auth = FirebaseAuth.getInstance()
        val user = auth.currentUser
        val email = user?.email.toString()


        confirmDelete.setOnClickListener {
            when {
                TextUtils.isEmpty(currentPass.text.toString()) -> {
                    Toast.makeText(
                        this@DeleteAccount,
                        "Enter current Password",
                        Toast.LENGTH_SHORT

                    ).show()
                }
                else -> {
                    if (user != null && user.email != null) {
                        // Get auth credentials from the user for re-authentication. The example below shows
                        // email and password credentials but there are multiple possible providers,
                        // such as GoogleAuthProvider or FacebookAuthProvider.
                        val password : String = currentPass.text.toString()
                        val credential = EmailAuthProvider
                            .getCredential(user.email!!, password)

                        // Prompt the user to re-provide their sign-in credentials
                        user.reauthenticate(credential)
                            .addOnCompleteListener {
                                if (it.isSuccessful) {
                                    Toast.makeText(
                                        this@DeleteAccount,
                                        "User Re-Authenticated Successfully",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    user.delete()
                                        .addOnCompleteListener { task ->
                                            if (task.isSuccessful) {
                                                Toast.makeText(
                                                    this@DeleteAccount,
                                                    "User deleted Successfully",
                                                    Toast.LENGTH_SHORT
                                                ).show()
                                                Log.d(ContentValues.TAG, "User account deleted.")
                                                val deleteIntent =
                                                    Intent(this@DeleteAccount, LoginActivity::class.java)
                                                deleteIntent.flags =
                                                    Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                                startActivity(deleteIntent)
                                                finish()
                                            } else {
                                                Log.d(ContentValues.TAG, "User account deleted.")
                                            }
                                        }
                                }
                            }
                    } else {
                        Toast.makeText(
                            this@DeleteAccount,
                            "User Re-Authenticated Failed",
                            Toast.LENGTH_SHORT

                        ).show()
                    }

                }
            }
        }
    }
}