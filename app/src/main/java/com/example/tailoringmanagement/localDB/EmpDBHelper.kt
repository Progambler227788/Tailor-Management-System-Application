package com.example.tailoringmanagement.localDB

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class EmpDBHelper(context: Context, factory: SQLiteDatabase.CursorFactory?):
    SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION)
{
    override fun onCreate(db: SQLiteDatabase?) {
        val query1 = "CREATE TABLE $TABLE_EMPLOYEE (" +
                "$COLUMN_EID INTEGER PRIMARY KEY, " +
                "$COLUMN_NAME TEXT, " +
                "$COLUMN_NO_SUITS TEXT, " +
                "$COLUMN_PHONE TEXT);"
        db?.execSQL(query1)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_EMPLOYEE")
        onCreate(db)
    }

    fun addEmployee(id: Int, name: String, suits: String, phone: String): Boolean{
        val values = ContentValues()

        values.put("$COLUMN_EID", id)
        values.put("$COLUMN_NAME", name)
        values.put("$COLUMN_NO_SUITS", suits)
        values.put("$COLUMN_PHONE", phone)

        val db = this.writableDatabase

        val result = db.insert("$TABLE_EMPLOYEE", null, values)
        db.close()

        return result != -1L
    }

    fun deleteEmployee(id: Int) {
        val db = this.writableDatabase
        db.execSQL("DELETE FROM $TABLE_EMPLOYEE WHERE $COLUMN_EID" +
                "=" + "$id")
        db.close()
    }

    fun getAllEmployees(): Cursor? {
        val db = this.readableDatabase
        return db.rawQuery("SELECT * FROM $TABLE_EMPLOYEE", null)
    }

    fun updateEmployeeInfo(id: Int, columnToChange: String, updatedValue: String){
        val db = this.writableDatabase
        db.execSQL("UPDATE $TABLE_EMPLOYEE " +
                "SET $columnToChange='$updatedValue' " +
                "WHERE $COLUMN_EID=$id")
        db.close()
    }

    companion object {
        private const val DATABASE_NAME = "EmployeeDirectory"
        private const val DATABASE_VERSION = 1

        const val TABLE_EMPLOYEE = "Employee"
        const val COLUMN_EID = "EmployeeID"
        const val COLUMN_NAME = "EmployeeName"
        const val COLUMN_NO_SUITS = "NumSuits"
        const val COLUMN_PHONE = "EmployeePhone"
    }
}