package com.example.a491bproject

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast


val DATABASENAME = "MY DATABASE"
val TABLENAME = "Ingredients"
val COL_INGREDIENT = "Ingredient"
val COL_QUANTITY = "Quantity"

class DataBaseHandler(var context: Context) : SQLiteOpenHelper(
    context, DATABASENAME, null,
    1
) {
    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "CREATE TABLE " + TABLENAME
        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        //onCreate(db);
    }

    fun insertData(user: AddIngredientsActivity.User) {
        val database = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COL_INGREDIENT, user.ingredient)
        contentValues.put(COL_QUANTITY, user.quantity)
        val result = database.insert(TABLENAME, null, contentValues)
        if (result == (0).toLong()) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
        }
    }
}