package com.example.a491bproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {
/*    override fun onCreate(savedInstanceState: Bundle?){
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_login)
}*/

    private companion object{
        private const val TAG = "LoginActivity"
        private const val RC_GOOGLE_SIGN_IN = 4800
    }

    private lateinit var auth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val loginEmailText =  findViewById<EditText>(R.id.editTextTextEmailAddress)
        val loginPasswordText = findViewById<EditText>(R.id.editTextTextPassword)

        // Login button configuration
        val loginBtn: Button = findViewById<Button>(R.id.loginBtn)
        //When User clicks Login it should log user in
        loginBtn.setOnClickListener {
            //Checks if fields are empty
            when{
                TextUtils.isEmpty(loginEmailText.text.toString()) -> {
                    Toast.makeText(this@LoginActivity,
                        "Enter email",
                        Toast.LENGTH_SHORT

                    ).show()
                }
                TextUtils.isEmpty(loginPasswordText.text.toString()) -> {
                    Toast.makeText(this@LoginActivity,
                        "Enter password",
                        Toast.LENGTH_SHORT

                    ).show()
                }
                else -> {
                    val email: String = loginEmailText.text.toString()
                    val password: String = loginPasswordText.text.toString()

                    //Sign in firebase instance with email and password
                    FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener { task ->

                            if (task.isSuccessful) {
                                val user = auth.currentUser
                                if (user != null) {
                                    if(user.isEmailVerified){
                                        Toast.makeText(
                                            this@LoginActivity,
                                            "Successfully Logged in",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                        //Navigates account to main and clear previous intents
                                        val loginIntent = Intent(this@LoginActivity, MainActivity::class.java)
                                        loginIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                        //loginIntent.putExtra("emailID", email)
                                        startActivity(loginIntent)
                                        finish()
                                    }
                                    else{
                                        Toast.makeText(
                                            this@LoginActivity,
                                            "Verify Email Address",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                        val loginIntent2 = Intent(this@LoginActivity, VerifyActivity::class.java)
                                        loginIntent2.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                        //loginIntent.putExtra("emailID", email)
                                        startActivity(loginIntent2)
                                        FirebaseAuth.getInstance().signOut()
                                        finish()

                                    }
                                }
                                else{
                                    Toast.makeText(
                                        this@LoginActivity,
                                        "Not Valid User",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }

                                //User is now logged in

 /*                               //Navigates account to main and clear previous intents
                                val loginIntent = Intent(this@LoginActivity, MainActivity::class.java)
                                loginIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                //loginIntent.putExtra("emailID", email)
                                startActivity(loginIntent)
                                finish()*/
                            }
                            //When Login fails print error
                            else {
                                Toast.makeText(
                                    this@LoginActivity,
                                    task.exception!!.message.toString(),
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                }
            }

        }

        // Configure Google Sign In
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("272160782836-g05i1g59bmtsfu9oti9i5b5q5v86bl06.apps.googleusercontent.com")
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)

        auth = FirebaseAuth.getInstance()
        val btnSignIn = findViewById<com.google.android.gms.common.SignInButton>(R.id.signButton)

        btnSignIn.setOnClickListener {
            signIn()
        }
        btnSignIn.setOnClickListener {
            val signInIntent = googleSignInClient.signInIntent
            startActivityForResult(signInIntent, RC_GOOGLE_SIGN_IN)
        }

        //Guest Log in button and transitions from login to main menu
        val guestBtn: Button = findViewById<Button>(R.id.guestBtn)
        guestBtn.setOnClickListener() {
            val guestIntent = Intent(this, MainActivity::class.java)
            startActivity(guestIntent)

        }

        //Navigates to Forgot Password Screen
        val forgot: Button = findViewById<Button>(R.id.forgotBtn)
        forgot.setOnClickListener(){
            val forgotIntent = Intent(this,ForgotPassword::class.java)
            startActivity(forgotIntent)
        }

        //Navigates to Register Screen
        val register: Button = findViewById<Button>(R.id.registerBtn)
        register.setOnClickListener(){
            val registerIntent = Intent(this,RegisterActivity::class.java)
            startActivity(registerIntent)
        }
    }
    ///Google sign in button
    private fun signIn() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_GOOGLE_SIGN_IN)
    }

    override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        updateUI(currentUser)
    }

    private fun updateUI(user: FirebaseUser?) {
        if (user == null){
            Log.w(TAG, "User is null, not going to move")
            return

        }
        startActivity(Intent(this, MainActivity::class.java))
        finish()

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_GOOGLE_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)!!
                Log.d(TAG, "firebaseAuthWithGoogle:" + account.id)
                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e)
            }
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithCredential:success")
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithCredential:failure", task.exception)
                    updateUI(null)
                }
            }
    }
}