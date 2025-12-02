package fr.eni.ecole.library


open class Item(
    var title: String,
    var author: String,
    var releaseYear: Int
) {
    var isAvailable : Boolean = true;
}

class User(var name: String, var id: Int)

interface Borrowable {
    fun borrow(user: User): Boolean;
    fun returnItem(user: User);
}

class Book(
    title: String,
    author: String,
    releaseYear : Int,
    genre: String
) : Item(title, author, releaseYear), Borrowable {
    override fun borrow(user: User): Boolean {
        if (isAvailable) {
            isAvailable = false
            println("$title has been borrowed by ${user.name}")
            return true
        } else {
            println("$title is already borrowed")
        }
        return false
    }

    override fun returnItem(user: User) {
        isAvailable = true
        println("$title has been returned by ${user.name}")
    }
}

fun main() {
    val kotlinProg = Book("Kotlin Programming", "Jane Doe", 2021, "Education")
    val flutterPro = Book("Flutter Programming", "Majid", 2023, "Education")
    val harryPotter = Book("Harry Potter", "JK Rowling", 1995, "Novel")

    val jeanMichel = User("Jean Michel", 1)
    val robert = User("Robert Jackson", 2)

    kotlinProg.borrow(jeanMichel)
    kotlinProg.borrow(robert)
    flutterPro.borrow(robert)
    kotlinProg.returnItem(jeanMichel)
    flutterPro.returnItem(robert)
}