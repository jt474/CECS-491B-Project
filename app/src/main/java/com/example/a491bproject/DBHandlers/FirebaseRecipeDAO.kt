package com.example.a491bproject.DBHandlers

import android.widget.Toast
import com.example.a491bproject.MainActivity
import com.example.a491bproject.models.IngredientModel
import com.example.a491bproject.models.RecipeModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.lang.NullPointerException


class FirebaseRecipeDAO(val auth: FirebaseAuth): RecipeDAO {
    private val dbRef:DatabaseReference
    private val userID: String?
    private val recipeParentStr= "Recipes"
    private val instructionParentStr = "Instructions"

    init{
        val user: FirebaseUser? = auth.currentUser;
        userID = user?.uid;
        dbRef = FirebaseDatabase.getInstance().reference;

    }


    override fun insertRecipe(model:RecipeModel): Int {
        val recipePush:DatabaseReference = dbRef.child(recipeParentStr).push()
        val recipePushID = recipePush.key!! //is null if the reference was to a root... but that shouldn't happen.

        val recipeMap = buildRecipeMap(model, recipePushID)

        var recipeNodeMap: HashMap<String, Any> = hashMapOf(
            "$recipeParentStr/$recipePushID" to recipeMap
        )

        updateChildren(recipeNodeMap)
        return 1 //Hack. turns out that with so many children being added... i cann't approach this the sql way.
    }

    override fun getRecipe(id: String): RecipeModel {
        TODO("Not yet implemented")
    }

    override fun updateRecipe(id: String): Int {
        TODO("Not yet implemented")
    }

    override fun deleteRecipe(id: String): Int {
        TODO("Not yet implemented")
    }

    fun buildRecipeMap(model:RecipeModel, recipeId:String): Map<String, String>{

        val map: HashMap<String, String> = hashMapOf(
            "recipeId" to recipeId,
            "authorId" to model.authorID,
            "title" to model.title
        )
        return map
    }

    fun buildRecipeIngredients(ingredients: List<IngredientModel>, recipeId:String): Int{
        //"$recipeParentStr/$recipePushID/Ingredients"
        for(ingredient in ingredients){
            dbRef.child(recipeParentStr).child(recipeId).child("Ingredients").child(ingredient.name).child("amount").setValue(ingredient.amount)
            dbRef.child(recipeParentStr).child(recipeId).child("Ingredients").child(ingredient.name).child("unit").setValue(ingredient.unit)
        }
        return 1; //Hack
    }

    fun updateChildren(nodeMap:HashMap<String,Any>){
        dbRef.updateChildren(nodeMap, object: DatabaseReference.CompletionListener{ //I have no idea why this is. https://stackoverflow.com/questions/59337641/the-read-and-write-calls-of-firebase-database-not-working-on-android
            override fun onComplete(error: DatabaseError?, ref: DatabaseReference) {
                TODO("Lack of Android experience blocks me from writing a good error.")
                //Toast.makeText(MainActivity, "Error occurred while inserting recipe", Toast.LENGTH_LONG).show()
                //
            }
        }
        )
    }



}

fun main(args: Array<String>) {
}

