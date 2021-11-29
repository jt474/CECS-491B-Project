package com.example.a491bproject

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth

class DeleteAccount : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete_account)

        var auth: FirebaseAuth = FirebaseAuth.getInstance()
        val currentPass = findViewById<EditText>(R.id.currentPassword)
        val confirmDelete: Button = findViewById<Button>(R.id.confirmDeleteBtn)
        val test: Button = findViewById<Button>(R.id.test)

        auth = FirebaseAuth.getInstance()
        val user = auth.currentUser
        val email = user?.email.toString()

        test.setOnClickListener{
            MaterialAlertDialogBuilder(this)
                .setTitle("Delete Confirmation")
                .setMessage("Are you sure you want to delete your account? ALL data will be wiped. DO you wish to continue?")
                .setNegativeButton("No") {dialog, which ->
                    Toast.makeText(
                        this@DeleteAccount,
                        "Cancelled",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                .setPositiveButton("Confirm Delete") {dialog, which ->
                    Toast.makeText(
                        this@DeleteAccount,
                        "Account Deleted",
                        Toast.LENGTH_SHORT
                    ).show()
                }.show()
        }


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
                            .addOnCompleteListener { task->
                                if (task.isSuccessful) {
                                    Toast.makeText(
                                        this@DeleteAccount,
                                        "User Re-Authenticated Successfully",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    MaterialAlertDialogBuilder(this)
                                        .setTitle("Delete Confirmation")
                                        .setMessage("Are you sure you want to delete your account? ALL data will be wiped. DO you wish to continue?")
                                        .setNegativeButton("No") {dialog, which ->
                                            Toast.makeText(
                                                this@DeleteAccount,
                                                "Cancelled",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        }
                                        .setPositiveButton("Confirm Delete") {dialog, which ->
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
                                        }.show()
                                }
                                else{
                                    Toast.makeText(
                                        this@DeleteAccount,
                                        task.exception!!.message.toString(),
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                    }
                    else {
                        Toast.makeText(
                            this@DeleteAccount,
                            "User Re-Authenticated Failed",
                            Toast.LENGTH_SHORT

                        ).show()
                    }

                }
            }
        }
        // Naming for menu
        val actionBar = supportActionBar

        if (actionBar != null) {
            actionBar.title = "Account Deletion"
        }
    }

    fun showAlertDialog(view: View){
        MaterialAlertDialogBuilder(this)
            .setTitle("Delete Confirmation")
            .setMessage("Are you sure you want to delete your account? ALL data will be wiped. DO you wish to continue?")
            .setNegativeButton("No") {dialog, which ->
                Toast.makeText(
                    this@DeleteAccount,
                    "Cancelled",
                    Toast.LENGTH_SHORT
                )
            }
            .setPositiveButton("Confirm Delete") {dialog, which ->
                Toast.makeText(
                    this@DeleteAccount,
                    "Account Deleted",
                    Toast.LENGTH_SHORT
                )
            }.show()
    }
}