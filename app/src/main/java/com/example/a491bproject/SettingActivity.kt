package com.example.a491bproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth

class SettingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)

        val emailId = intent.getStringExtra("emailID")
        val settingEmail: TextView = findViewById<TextView>(R.id.settingEmail)
        settingEmail.text = "Email : $emailId"


        //Signout
        val logoutBtn: Button = findViewById<Button>(R.id.logoutBtn)
        logoutBtn.setOnClickListener{
            FirebaseAuth.getInstance().signOut()
            val logoutIntent = Intent(this@SettingActivity, LoginActivity::class.java)
            logoutIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(logoutIntent)
            finish()
        }

        // Naming for menu
        val actionBar = supportActionBar

        if(actionBar != null){
            actionBar.title = "Settings"
        }
    }
}