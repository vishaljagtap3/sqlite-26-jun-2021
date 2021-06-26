package `in`.bitcode.sqlite

class Product(var id : Int, var title : String, var price : Int) {
    override fun toString(): String {
        return "$id - $title - $price"
    }
}