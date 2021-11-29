package com.example.a491bproject

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.a491bproject.databinding.ActivityContactBinding
import com.google.firebase.auth.FirebaseAuth

class Contact : AppCompatActivity() {

    lateinit var binding : ActivityContactBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact)

        //Navigate to Terms
        val sendBtn: Button = findViewById<Button>(R.id.send)
        sendBtn.setOnClickListener{
            val subject = findViewById<EditText>(R.id.subject)
            val message = findViewById<EditText>(R.id.message)

            val sendSub = subject.text.toString()
            val sendMsg = message.text.toString()
            val address = "ViridianVira@gmail.com"

            val contactIntent = Intent(Intent.ACTION_SEND)
            contactIntent.type = "message/rfc822"
            contactIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf(address))
            contactIntent.putExtra(Intent.EXTRA_SUBJECT, sendSub)
            contactIntent.putExtra(Intent.EXTRA_TEXT, sendMsg)
            try {
                startActivity(Intent.createChooser(contactIntent, "Send mail:"))
            } catch (ex: ActivityNotFoundException) {
                Toast.makeText(
                    this@Contact,
                    "There are no email apps installed.",
                    Toast.LENGTH_SHORT
                ).show()
            }

            /*val subject = findViewById<EditText>(R.id.subject)
            val message = findViewById<EditText>(R.id.message)

            val sendSub = subject.text.toString()
            val sendMsg = message.text.toString()
            val address = "ViridianVira@gmail.com"


            val contactIntent = Intent(Intent.ACTION_SEND).apply{
                data = Uri.parse("mailto:")
                putExtra(Intent.EXTRA_EMAIL, address)
                putExtra(Intent.EXTRA_SUBJECT, sendSub)
                putExtra(Intent.EXTRA_TEXT, sendMsg)
            }

            try {
                startActivity(Intent.createChooser(contactIntent, "Send mail..."))
            } catch (ex: ActivityNotFoundException) {
                Toast.makeText(
                    this@Contact,
                    "There are no email clients installed.",
                    Toast.LENGTH_SHORT
                ).show()*/
            }
        }
    }