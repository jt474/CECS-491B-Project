package com.example.a491bproject

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.example.a491bproject.adapters.CreateRecipeFragmentStateAdapter
import com.example.a491bproject.interfaces.CreateRecipeViewModelListener
import com.example.a491bproject.models.IngredientModel
import com.example.a491bproject.models.InstructionModel
import com.example.a491bproject.models.RecipeAboutModel
import com.example.a491bproject.models.UserRecipesModel
import com.example.a491bproject.viewmodels.CreateRecipeViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class CreateFirebaseRecipeActivity : AppCompatActivity(),CreateRecipeViewModelListener {

    private var tabTitles: ArrayList<String> = arrayListOf<String>("About", "Ingredients", "Instructions")
    private lateinit var mViewModel:CreateRecipeViewModel
    private lateinit var btnCreateRecipe:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_firebase_recipe)

        //Initialize UI Elements
        val viewPager = findViewById<ViewPager2>(R.id.view_pager_CreateRecipe)
        val tabLayout = findViewById<TabLayout>(R.id.tab_layout_CreateRecipe)
        btnCreateRecipe = findViewById<Button>(R.id.btnCreateRecipe)
        btnCreateRecipe.isEnabled = false
        btnCreateRecipe.setOnClickListener{
            saveToRealtimeDatabase()
        }

        mViewModel = ViewModelProvider(this).get(CreateRecipeViewModel::class.java)
        mViewModel.setViewModelListener(this)

        val adapter = CreateRecipeFragmentStateAdapter(supportFragmentManager,lifecycle)
        viewPager.adapter = adapter

        TabLayoutMediator(tabLayout, viewPager){
                tab, position -> tab.text = tabTitles[position];

        }.attach()

    }

    override fun onChanged() {
        btnCreateRecipe.isEnabled = mViewModel.isNotMissingInfo()
    }

    private fun saveToRealtimeDatabase(){
        val db = FirebaseDatabase.getInstance()
        val recipeRoot = db.getReference("Recipes")
        val pushedPostRef = recipeRoot.push()
        val recipeID = pushedPostRef.key
        if (recipeID != null) {
            saveToRecipes(recipeID,recipeRoot)
            saveToInstructions(recipeID,db.getReference("Instructions"))
            saveToIngredients(recipeID,db.getReference("Ingredients"))
            saveToAboutRecipe(recipeID,db.getReference("AboutRecipe"))
        }

    }

    private fun saveToRecipes(recipeID:String, reference: DatabaseReference){
        val model = UserRecipesModel(getCurrentUserUID(), recipeID, mViewModel.getRecipeTitle())
        var recipe:Map<String, UserRecipesModel> = hashMapOf(recipeID to model)
        reference.updateChildren(recipe)
    }

    private fun saveToIngredients(recipeID:String, reference: DatabaseReference){
        var ingredients = hashMapOf<String, IngredientModel>()
        for(ingredient in mViewModel.getIngredients()){
            ingredients[ingredient.name] = ingredient
        }
        val ref = reference.child(recipeID)
        ref.setValue(ingredients)
    }

    private fun saveToInstructions(recipeID:String, reference:DatabaseReference){
        var instructions = hashMapOf<String, InstructionModel>()
        for(instruction in mViewModel.getInstructions()){
            instructions[instruction.number.toString()] = instruction
        }
        val ref = reference.child(recipeID)
        ref.setValue(instructions)
    }

    private fun saveToAboutRecipe(recipeID: String, reference:DatabaseReference){
        val model = RecipeAboutModel(getCurrentDate(),mViewModel.getDescription())
        var about:Map<String, RecipeAboutModel> = hashMapOf(recipeID to model)
        reference.updateChildren(about)
    }

    private fun getCurrentUserUID():String {
        val authorID = FirebaseAuth.getInstance().currentUser!!.uid
        Log.d("CreateViewModel", "AuthorID set to $authorID")
        return authorID
    }

    private fun getCurrentDate(): String{
        val sdf = SimpleDateFormat("MM/dd/yyyy")
        val lastUpdatedStr = sdf.format(Date())
        Log.d("getCurrentDate", "ViewModel.dateUpdated changed to $lastUpdatedStr")
        return lastUpdatedStr
    }


}