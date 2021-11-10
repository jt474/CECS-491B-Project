package com.example.a491bproject

import android.content.ContentValues.TAG
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
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class ChangePasswordActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_password)

        val changeBtn: Button = findViewById<Button>(R.id.chgPassBtn)
        val currentPass = findViewById<EditText>(R.id.currentPass)
        val newPass = findViewById<EditText>(R.id.newPass)
        val confirmPass = findViewById<EditText>(R.id.confirmPass)

        auth = FirebaseAuth.getInstance()

        changeBtn.setOnClickListener {
            //Checks if fields are empty
            when {
                TextUtils.isEmpty(currentPass.text.toString()) -> {
                    Toast.makeText(
                        this@ChangePasswordActivity,
                        "Enter current Password",
                        Toast.LENGTH_SHORT

                    ).show()
                }
                TextUtils.isEmpty(newPass.text.toString()) -> {
                    Toast.makeText(
                        this@ChangePasswordActivity,
                        "Enter new password",
                        Toast.LENGTH_SHORT

                    ).show()
                }
                TextUtils.isEmpty(confirmPass.text.toString()) -> {
                    Toast.makeText(
                        this@ChangePasswordActivity,
                        "Enter re-enter new password",
                        Toast.LENGTH_SHORT

                    ).show()
                }
                else -> {
                    val currentPassword: String = currentPass.text.toString()
                    val newPassword: String = newPass.text.toString()
                    val confirmPassword: String = confirmPass.text.toString()

                    if (newPassword == confirmPassword){
                        val user = auth.currentUser
                        if(user != null && user.email != null) {
                            // Get auth credentials from the user for re-authentication. The example below shows
                            // email and password credentials but there are multiple possible providers,
                            // such as GoogleAuthProvider or FacebookAuthProvider.
                            val credential = EmailAuthProvider
                                .getCredential(user.email!!, currentPassword)

                            // Prompt the user to re-provide their sign-in credentials
                            user.reauthenticate(credential)
                                .addOnCompleteListener {
                                    if(it.isSuccessful){
                                        Toast.makeText(
                                            this@ChangePasswordActivity,
                                            "User Re-Authenticated Successfully",
                                            Toast.LENGTH_SHORT

                                        ).show()

                                        //Changes User Password
                                        user!!.updatePassword(newPassword)
                                            .addOnCompleteListener { task ->
                                                if (task.isSuccessful) {
                                                    Log.d(TAG, "User password updated.")
                                                    Toast.makeText(
                                                        this@ChangePasswordActivity,
                                                        "Password Changed",
                                                        Toast.LENGTH_SHORT

                                                    ).show()
                                                    auth.signOut()
                                                    val loginIntent = Intent(this, LoginActivity::class.java)
                                                    loginIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                                    startActivity(loginIntent)

                                                }
                                            }
                                    }
                                    else{
                                        Toast.makeText(
                                            this@ChangePasswordActivity,
                                            "User Re-Authenticated Failed",
                                            Toast.LENGTH_SHORT

                                        ).show()
                                    }
                                }

                        }
                        else{
                            Log.d(TAG, "User null.")
                            Toast.makeText(
                                this@ChangePasswordActivity,
                                "User is null",
                                Toast.LENGTH_SHORT

                            ).show()
                        }
                    }
                    else {
                        Toast.makeText(
                            this@ChangePasswordActivity,
                            "New Password does not match",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }
}