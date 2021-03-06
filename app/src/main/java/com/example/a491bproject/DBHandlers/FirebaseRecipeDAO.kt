package com.example.a491bproject.DBHandlers

import android.util.Log
import com.example.a491bproject.models.IngredientModel
import com.example.a491bproject.models.RecipeFirebaseModel
import com.example.a491bproject.models.UserRecipesModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import com.google.firebase.database.ktx.getValue


class FirebaseRecipeDAO(val auth: FirebaseAuth): RecipeDAO {
    private val dbRef:DatabaseReference
    private val userID: String?
    private val recipeParentStr= "Recipes"
    private val instructionParentStr = "Instructions"

    init{
        val user: FirebaseUser? = auth.currentUser;
        userID = user?.uid;
        dbRef = FirebaseDatabase.getInstance().reference;
        Log.d("FirebaseDAO Init", "Constructing FirebaseRecipeDAO. Userid: $userID dbRef: $dbRef")

    }

    override fun getUserRecipes(): MutableList<UserRecipesModel> {
        val list = mutableListOf<UserRecipesModel>()
        val getUserRecipeQuery = dbRef.child("Recipes")
        getUserRecipeQuery.orderByChild("authorID").equalTo(userID).addChildEventListener(object: ChildEventListener{
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                Log.d("Datasnapshot", "${snapshot.key} ${snapshot.getValue<UserRecipesModel>()}")
                if(snapshot.exists()){
                    val model = snapshot.getValue<UserRecipesModel>()
                    if(model != null) list.add(model)
                }
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
                TODO("Not yet implemented")
            }

            override fun onChildRemoved(snapshot: DataSnapshot) {
                TODO("Not yet implemented")
            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
                TODO("Not yet implemented")
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
        return list
    }

    override fun insertRecipe(firebaseModel:RecipeFirebaseModel): Int {
        val recipePush:DatabaseReference = dbRef.child(recipeParentStr).push()
        val recipePushID = recipePush.key!! //is null if the reference was to a root... but that shouldn't happen.

        val recipeMap = buildRecipeMap(firebaseModel, recipePushID)

        var recipeNodeMap: HashMap<String, Any> = hashMapOf(
            "$recipeParentStr/$recipePushID" to recipeMap
        )

        updateChildren(recipeNodeMap)
        return 1 //Hack. turns out that with so many children being added... i cann't approach this the sql way.
    }

    override fun getRecipe(id: String): RecipeFirebaseModel {
        TODO("Not yet implemented")
    }



    override fun updateRecipe(id: String): Int {
        TODO("Not yet implemented")
    }

    override fun deleteRecipe(id: String): Int {
        TODO("Not yet implemented")
    }

    fun buildRecipeMap(firebaseModel:RecipeFirebaseModel, recipeId:String): Map<String, String>{

        val map: HashMap<String, String> = hashMapOf(
            "recipeId" to recipeId,
            "authorId" to firebaseModel.authorID,
            "title" to firebaseModel.title
        )
        return map
    }

    fun buildRecipeIngredients(ingredients: List<IngredientModel>, recipeId:String): Int{
        //"$recipeParentStr/$recipePushID/Ingredients"
        for(ingredient in ingredients){
            //dbRef.child(recipeParentStr).child(recipeId).child("Ingredients").child(ingredient.name).child("amount").setValue(ingredient.amount)
            //dbRef.child(recipeParentStr).child(recipeId).child("Ingredients").child(ingredient.name).child("unit").setValue(ingredient.unit)
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

