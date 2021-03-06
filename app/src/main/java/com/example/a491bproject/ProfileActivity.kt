package com.example.a491bproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile_setting)


        //User created email display
/*        val emailId = intent.getStringExtra("emailID")
        val settingEmail: TextView = findViewById<TextView>(R.id.settingEmail)
        settingEmail.text = "Email : $emailId"*/

        //Email display by getting current user from firebase auth
        var auth: FirebaseAuth = FirebaseAuth.getInstance()
        val user = auth.currentUser
        val email = user?.email.toString();
        val settingEmail: TextView = findViewById<TextView>(R.id.settingEmail)
        settingEmail.text = "Email : $email"

        //Change Password
        val changePassBtn: Button = findViewById<Button>(R.id.changePassBtn)
        changePassBtn.setOnClickListener{
            val changePassIntent = Intent(this@ProfileActivity, ChangePasswordActivity::class.java)
            startActivity(changePassIntent)
            finish()
        }

        //Delete Account
        val deleteBtn: Button = findViewById<Button>(R.id.deleteBtn)
        deleteBtn.setOnClickListener{
            val deleteIntent = Intent(this@ProfileActivity, DeleteAccount::class.java)
            startActivity(deleteIntent)
            finish()
        }

        //Signout
        val logoutBtn: Button = findViewById<Button>(R.id.logoutBtn)
        logoutBtn.setOnClickListener{
            FirebaseAuth.getInstance().signOut()
            val logoutIntent = Intent(this@ProfileActivity, LoginActivity::class.java)
            logoutIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(logoutIntent)
            finish()
        }

        // Naming for menu
        val actionBar = supportActionBar

        if(actionBar != null){
            actionBar.title = "Profile"
        }
    }
}