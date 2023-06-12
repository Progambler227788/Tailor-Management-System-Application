package com.example.tailoringmanagement.localDB

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class OrderDBHelper(context: Context, factory: SQLiteDatabase.CursorFactory?):
    SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION)

{
    override fun onCreate(db: SQLiteDatabase?) {
        val query1 = "CREATE TABLE $TABLE_ORDER (" +
                "$COLUMN_OID INTEGER PRIMARY KEY, " +
                "$COLUMN_CID INTEGER NOT NULL, " +
                "$COLUMN_EID INTEGER NOT NULL, " +
                "$COLUMN_PRICE REAL NOT NULL, " +
                "$COLUMN_DATE TEXT);"
        db?.execSQL(query1)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_ORDER")
        onCreate(db)
    }
    // functions to add, delete and update order

    fun addOrder(oid: Int, cid: Int, eid: Int, payment: Float, date: String): Boolean{
        val values = ContentValues()

        values.put(COLUMN_OID, oid)
        values.put(COLUMN_CID, cid)
        values.put(COLUMN_EID, eid)
        values.put(COLUMN_PRICE, payment)
        values.put(COLUMN_DATE, date)

        val db = this.writableDatabase

        val result = db.insert(TABLE_ORDER, null, values)
        db.close()

        return result != -1L
    }

    fun deleteOrder(oid: Int) {
        val db = this.writableDatabase
        db.execSQL("DELETE FROM $TABLE_ORDER WHERE $COLUMN_OID" +
                "=" + "$oid")
        db.close()
    }

    fun getAllOrders(): Cursor? {
        val db = this.readableDatabase
        return db.rawQuery("SELECT * FROM $TABLE_ORDER", null)
    }

    fun updateOrdersInfo(id: Int, columnToChange: String, updatedValue: String){
        val db = this.writableDatabase
        db.execSQL("UPDATE $TABLE_ORDER " +
                "SET $columnToChange='$updatedValue' " +
                "WHERE $COLUMN_OID=$id")
        db.close()
    }

    companion object {
        // database name and version
        private const val DATABASE_NAME = "OrderDirectory"
        private const val DATABASE_VERSION = 1

        // table name

        const val TABLE_ORDER = "Orders"

        // column names
        const val COLUMN_OID= "OrderID"
        const val COLUMN_CID = "CustomerID"
        const val COLUMN_EID = "EmployeeID"
        const val COLUMN_PRICE = "OrderPrice"
        const val COLUMN_DATE = "Date"
    }
}