package `in`.bitcode.sqlite

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class ProductsDBHelper(context : Context, dbName : String, factory: SQLiteDatabase.CursorFactory?, version : Int)
    : SQLiteOpenHelper(context, dbName, factory, version) {

    override fun onCreate(db: SQLiteDatabase?) {
        Log.e("tag", "SQLiteOpenHelper: onCreate")
        db?.execSQL("create table products (id integer primary key, title text, price integer)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        //code to update the db structure
        //db?.execSQL("alter table products add is_read tinyint")
    }

    override fun onDowngrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        super.onDowngrade(db, oldVersion, newVersion)
    }
}

