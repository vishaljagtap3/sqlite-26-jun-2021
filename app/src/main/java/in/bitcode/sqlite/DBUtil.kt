package `in`.bitcode.sqlite

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase

class DBUtil(context : Context) {
    private var db : SQLiteDatabase? = null

    init {
        db = ProductsDBHelper(context, "db_products", null, 2).writableDatabase
    }

    fun close() {
        db?.close()
    }

    fun addProduct(id : Int, title : String, price : Float) : Boolean {
        var values = ContentValues()
        values.put("id", id)
        values.put("title", title)
        values.put("price", price)

        var rownum = db?.insert(
            "products",
            null,
            //"purchase_date, city",
            values
        )

        if (rownum?.compareTo( -1) == 0 ) {
            return false
        }

        return true
    }


     fun deleteProduct(id : Int) : Boolean{
        var id = 101
        var count = db?.delete(
            "products",
            "id = ?",
            arrayOf("$id")
        )
         if (count?.compareTo(0) == 0 ) {
             return false
         }

         return true
    }
}