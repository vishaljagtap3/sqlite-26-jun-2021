package `in`.bitcode.sqlite

import android.app.Activity
import android.content.ContentValues
import android.database.Cursor
import android.database.DatabaseErrorHandler
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    lateinit var db: SQLiteDatabase
    var products = ArrayList<Product>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initDB();
        addProducts();
        populate();
        updateProducts()
        mt("----------")
        populate()
        deleteProducts()
        mt("----------")
        populate()
    }

    private fun misc() {
        var c : Cursor = db.rawQuery(
            "select id, title, price from products where price > ? and price < ?",
            arrayOf("100000", "150000")
        )
    }

    private fun deleteProducts() {
        var id = 101
        var count = db.delete(
            "products",
            "id = ?",
            arrayOf("$id")
        )
        if(count > 0) {
            mt("deletion successful!")
        }
        else {
            mt("deletion failed!")
        }

    }

    private fun updateProducts() {
        var id = 101

        var values = ContentValues()
        values.put("title", "Macbook Pro")
        values.put("price", 340000)

        var count = db.update(
            "products",
            values,
            "id = ?",
            arrayOf("$id")
        )
        if(count > 0) {
            mt("Product updated!")
        }
        else {
            mt("Unable to update product!")
        }
    }

    private fun populate() {

        /*var c : Cursor = db.query(
            "products",
            arrayOf("id", "title", "price"),
            "price > ? and price < ?",
            arrayOf("100000", "150000"),
            null,
            null,
            "price desc"
        )*/

        products.clear()

        var c = db.query(
            "products",
            null,
            null,
            null,
            null,
            null,
            "id"
        )

        while (c.moveToNext()) {
            products.add(
                Product(
                    c.getInt(0),
                    c.getString(1),
                    c.getInt(2)
                )
            )
        }
        for(product in products) {
            mt(product.toString())
        }

    }


    private fun addProducts() {

        var values = ContentValues()
        values.put("id", 101)
        values.put("title", "Macbook")
        values.put("price", 190000)

        var rownum = db.insert(
            "products",
            null,
            //"purchase_date, city",
            values
        )

        if(rownum > -1) {
            mt("Data inserted successfully!")
        }
        else {
            mt("Insertion failed!");
        }

        values.put("id", 50)
        values.put("title", "Dell XPS")
        values.put("price", 90000)
        rownum = db.insert(
            "products",
            null,
            values
        )

        values.put("id", 230)
        values.put("title", "Gaming Machine")
        values.put("price", 900000)
        rownum = db.insert(
            "products",
            null,
            values
        )

        values.put("id", 190)
        values.put("title", "System 76")
        values.put("price", 120000)
        rownum = db.insert(
            "products",
            null,
            values
        )

    }

    private fun mt(text : String) {
        Log.e("tag", text)
    }


    private fun initDB() {
        db = openOrCreateDatabase(
            "db_products",
            Activity.MODE_PRIVATE,
            null
        )

        try {
            db.execSQL(
                "create table products (id integer primary key, title text, price integer)"
            )
        } catch (e: Exception) {
        }

        /*
        db.execSQL(
            "create table customers(? integer primary key, ? text, ? integer)",
            arrayOf("cust_id", "name", "rating")
        )
        */

    }
}