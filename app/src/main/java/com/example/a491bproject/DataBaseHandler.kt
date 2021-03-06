package com.example.a491bproject

import android.annotation.SuppressLint
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteException
import android.widget.TextView

class DataBaseHandler(context: Context): SQLiteOpenHelper(context,DATABASE_NAME,null,DATABASE_VERSION) {
    companion object {
        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "IngredientDatabase"
        private val TABLE_CONTACTS = "IngredientTable"
        private val KEY_INGREDIENT = "ingredient"
        private val KEY_QUANTITY = "quantity"
    }
    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_CONTACTS_TABLE = ("CREATE TABLE " + TABLE_CONTACTS + "("
                + KEY_INGREDIENT + " STRING, " + KEY_QUANTITY + " STRING" + ")")
        db?.execSQL(CREATE_CONTACTS_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_CONTACTS")
        onCreate(db)
    }

    fun addIngredient(quantity: String, ingredient: String) {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_QUANTITY, quantity)
        contentValues.put(KEY_INGREDIENT, ingredient)
        db.insert(TABLE_CONTACTS, null, contentValues)
        db.close()
    }

    fun updateIngredient(quantity: String, ingredient: String) {
//        val db = this.writableDatabase
//        db.deleteIngredient(ingredient)
    }

    fun clearIngredient() {
        val db = this.writableDatabase
        db.delete(TABLE_CONTACTS, null, null)
        db.close()
    }

    @SuppressLint("Range")
    fun viewIngredient(): ArrayList<String> {
        val ingredientList:ArrayList<String> = ArrayList<String>()
        val selectQuery = "SELECT  * FROM $TABLE_CONTACTS"
        val db = this.readableDatabase
        var cursor: Cursor?
        try{
            cursor = db.rawQuery(selectQuery, null)
        }catch (e: SQLiteException) {
            db.execSQL(selectQuery)
            return ArrayList()
        }
        var userIngredient: String
        var userQuantity: String
        if (cursor.moveToFirst()) {
            do {
                userIngredient = cursor.getString(cursor.getColumnIndex("ingredient"))
                userQuantity = cursor.getString(cursor.getColumnIndex("quantity"))
                val ingredient= "$userIngredient , $userQuantity"
                ingredientList.add(ingredient)
            } while (cursor.moveToNext())
        }
        return ingredientList
    }

    fun deleteIngredient(ingredient: String) {
        val db = this.writableDatabase
        db!!.delete(TABLE_CONTACTS, "$KEY_QUANTITY=?", arrayOf(ingredient))
        db.close()
    }
}

//import android.content.ContentValues
//import android.content.Context
//import android.database.sqlite.SQLiteDatabase
//import android.database.sqlite.SQLiteOpenHelper
//import android.widget.Toast
//
//
//val DATABASENAME = "MY DATABASE"
//val TABLENAME = "Ingredients"
//val COL_INGREDIENT = "Ingredient"
//val COL_QUANTITY = "Quantity"
//
//class DataBaseHandler(var context: Context) : SQLiteOpenHelper(
//    context, DATABASENAME, null,
//    1
//) {
//    override fun onCreate(db: SQLiteDatabase?) {
//        val createTable = "CREATE TABLE " + TABLENAME
//        db?.execSQL(createTable)
//    }
//
//    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
//        onCreate(db);
//    }
//
//    fun insertData(user: IngredientsActivity.User): Long {
//        val database = this.writableDatabase
//        val contentValues = ContentValues()
//        contentValues.put(COL_INGREDIENT, user.ingredient)
//        contentValues.put(COL_QUANTITY, user.quantity)
//        val result = database.insert(TABLENAME, null, contentValues)
//        database.close()
//        return result
////        if (result == (0).toLong()) {
////            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
////        } else {
////            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
////        }
//    }
//
//    fun updateData(user: IngredientsActivity.User, newQuantity: Double): Int {
//        val database = this.writableDatabase
//        val contentValues = ContentValues()
//        contentValues.put(COL_INGREDIENT, user.ingredient)
//        contentValues.put(COL_QUANTITY, user.quantity)
//        val result = database.update(TABLENAME, contentValues, "Quantity="+user.quantity, null)
//        database.close()
//        return result
////        val database = this.writableDatabase
//////        val contentValues = ContentValues()
////        database.execSQL("UPDATE TABLENAME SET COL_QUANTITY='newQuantity' WHERE COL_INGREDIENT=user.ingredient ");
//    }
//
//    fun printData() {
//
//    }
//}