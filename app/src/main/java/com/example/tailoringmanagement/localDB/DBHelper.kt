package com.example.tailoringmanagement.localDB

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context, factory: SQLiteDatabase.CursorFactory?):
    SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION)
{
    override fun onCreate(db: SQLiteDatabase?) {
        val query1 =("CREATE TABLE $TABLE_CUSTOMER (" +
                "$COLUMN_CID INTEGER PRIMARY KEY," +
                "$COLUMN_NAME TEXT," +
                "$COLUMN_PHONE TEXT);")

        db?.execSQL(query1)
    }

    fun addCustomer(id: Int, name: String, phone: String): Boolean {
        val values = ContentValues()

        values.put(COLUMN_CID, id)
        values.put(COLUMN_NAME, name)
        values.put(COLUMN_PHONE, phone)

        val db = this.writableDatabase

        val result = db.insert(TABLE_CUSTOMER, null, values)

        db.close()

        if (result == (-1).toLong())
            return true
        return false
    }

    fun deleteCustomer(id: Int) {
        val db = this.writableDatabase
        db.execSQL("DELETE FROM $TABLE_CUSTOMER WHERE $COLUMN_CID" +
                "=" + "$id")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_CUSTOMER")
        onCreate(db)
    }

    fun getAllCustomers(): Cursor {
        val db = this.readableDatabase
        return db.rawQuery("SELECT * FROM $TABLE_CUSTOMER", null)
    }

    companion object {
        private const val DATABASE_NAME = "CustomerDirectory"
        private const val DATABASE_VERSION = 1

        const val TABLE_CUSTOMER = "Customer"
        const val COLUMN_CID = "CustomerID"
        const val COLUMN_NAME = "CustomerName"
        const val COLUMN_PHONE = "CustomerPhone"
    }
}