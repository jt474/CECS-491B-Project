package com.example.a491bproject

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.a491bproject.DBHandlers.FirebaseRecipeDAO
import com.example.a491bproject.DBHandlers.RecipeDAO
import com.example.a491bproject.databinding.ActivityUserFirebaseRecipesBinding
import com.example.a491bproject.interfaces.onClickUserRecipeListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.getValue
import com.example.a491bproject.adapters.UserRecipesAdapter as UserRecipesAdapter
import com.example.a491bproject.models.UserRecipesModel as UserRecipesModel

class UserFirebaseRecipesActivity : AppCompatActivity(), onClickUserRecipeListener {


    private lateinit var auth: FirebaseAuth
    private lateinit var dbHandler: RecipeDAO
    private lateinit var testRecipesList: MutableList<UserRecipesModel>
    private lateinit var recyclerView:RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_firebase_recipes)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        auth = FirebaseAuth.getInstance()
        testRecipesList = mutableListOf<UserRecipesModel>()


        val adapter = UserRecipesAdapter(testRecipesList, this)
        recyclerView = findViewById<RecyclerView>(R.id.rvUserRecipes)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        val user: FirebaseUser? = auth.currentUser;
        val userID = user?.uid;
        val dbRef = FirebaseDatabase.getInstance().reference
        val getUserRecipeQuery = dbRef.child("Recipes")
        getUserRecipeQuery.orderByChild("authorID").equalTo(userID).addChildEventListener(UserRecipesFirebaseChildListener())

        val actionBar = supportActionBar

        if (actionBar != null) {
            actionBar.title = "Recipes"
        }

    }

    private inner class UserRecipesFirebaseChildListener: ChildEventListener {
        override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
            Log.d("Datasnapshot", "${snapshot.key} ${snapshot.getValue<UserRecipesModel>()}")
            if(snapshot.exists()){
                val model = snapshot.getValue<UserRecipesModel>()
                if(model != null) testRecipesList.add(model)
                recyclerView.adapter!!.notifyDataSetChanged() //Hack for now.
            }
        }

        override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
            TODO("Not yet implemented")
        }

        override fun onChildRemoved(snapshot: DataSnapshot) {
            Log.d("onChildRemoved", "${snapshot.key} ${snapshot.getValue<UserRecipesModel>()}")
            if(snapshot.exists()){
                val model = snapshot.getValue<UserRecipesModel>()
                if(model != null) {
                    testRecipesList.remove(model)
                    recyclerView.adapter!!.notifyDataSetChanged() //Hack for now.
                }
            }
        }

        override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
            TODO("Not yet implemented")
        }

        override fun onCancelled(error: DatabaseError) {
            Log.d("onCancelled","FirebaseChildListener ran into an error. ${error.toString()}" )
        }
    }

    override fun onEditClick(model: UserRecipesModel) {
        val intent = Intent(this, UpdateFirebaseRecipeActivity::class.java)
        intent.putExtra(getString(R.string.RecipeID), model.recipeID)
        intent.putExtra(getString(R.string.RecipeTitle), model.title)
        intent.putExtra(getString(R.string.AuthorID), model.authorID)
        startActivity(intent)
    }

    override fun onSelectClick(model: UserRecipesModel) {
        val intent = Intent(this, RecipeFirebaseInfoActivity::class.java)
        intent.putExtra(getString(R.string.RecipeID), model.recipeID)
        intent.putExtra(getString(R.string.RecipeTitle), model.title)
        startActivity(intent)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }



}
