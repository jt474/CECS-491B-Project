package com.example.a491bproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.a491bproject.adapters.ButtonAdapter

class Settings : AppCompatActivity(), ButtonAdapter.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        val recyclerView: RecyclerView = findViewById(R.id.rv_settings)
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        recyclerView.adapter = ButtonAdapter(this, fetchList(), this)

//        //Navigate to Profile
//        val accountBtn: Button = findViewById<Button>(R.id.account)
//        accountBtn.setOnClickListener{
//            val accountIntent = Intent(this, ProfileActivity::class.java)
//            startActivity(accountIntent)
//        }
//
//        //Navigate to Terms
//        val termBtn: Button = findViewById<Button>(R.id.terms)
//        termBtn.setOnClickListener{
//            val termsIntent = Intent(this, Terms::class.java)
//            startActivity(termsIntent)
//        }
//
//        //Contact
//        val contactBtn: Button = findViewById<Button>(R.id.contact)
//        contactBtn.setOnClickListener{
//            val contactIntent = Intent(this, Contact::class.java)
//            startActivity(contactIntent)
//        }
        // Naming for menu
        val actionBar = supportActionBar

        if (actionBar != null) {
            actionBar.title = "Settings"
        }
    }

    private fun fetchList(): ArrayList<com.example.a491bproject.models.Button> {
        val list = arrayListOf<com.example.a491bproject.models.Button>()

        list.add(com.example.a491bproject.models.Button(R.drawable.profile_activity, "Profile"))
        list.add(com.example.a491bproject.models.Button(R.drawable.terms, "Terms"))
        list.add(com.example.a491bproject.models.Button(R.drawable.contact, "Contact"))

        return list
    }

    override fun onClickListener(data: Int) {
        when (data) {
            0 -> {
                val accountIntent = Intent(this, ProfileActivity::class.java)
                startActivity(accountIntent)
            }
            1 -> {
                val termsIntent = Intent(this, Terms::class.java)
                startActivity(termsIntent)
            }
            2 -> {
                val contactIntent = Intent(this, Contact::class.java)
                startActivity(contactIntent)
            }
        }

        // Naming for menu
        val actionBar = supportActionBar

        if(actionBar != null){
            actionBar.title = "Settings"
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

}