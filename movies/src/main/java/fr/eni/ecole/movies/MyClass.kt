package fr.eni.ecole.movies

var alreadyWatchedMovies = mutableListOf<String>();
var toWatchMovies = mutableListOf<String>();

fun main() {
    do {
        displayIntroductionMessage();
        val input = readln().toInt();
        when (input) {
            1 -> {
                println("Veuillez renseigner le film")
                val movie = readln();
                // Valider les entrées utilisateurs
                alreadyWatchedMovies = addMovie(alreadyWatchedMovies, movie);
            }
            2 -> {
                println("Veuillez renseigner le film")
                val movie = readln();
                toWatchMovies = addMovie(toWatchMovies, movie);
            }
            3 -> displayMovies();
            else -> println("Merci d'avoir utilisé notre application")
        }
    } while (input in listOf(1, 2, 3))
}


fun displayMovies() {
    println("Liste des films déjà vus")
    for ((index, movie) in alreadyWatchedMovies.withIndex()) {
        println("${index + 1} - $movie")
    }
    println("Liste des films à voir")
    for ((index, movie) in toWatchMovies.withIndex()) {
        println("${index + 1} - $movie")
    }
}

fun addMovie(movies: List<String>, movie: String): MutableList<String> {
    val cloneMovies = movies.toMutableList();
    cloneMovies.add(movie);
    return cloneMovies.toMutableList();
}

fun displayIntroductionMessage() {
    val introduction = """
        Choisissez
        1 - Ajouter un film déjà vu
        2 - Ajouter un film/série à voir
        3 - Voir toute la liste
        4 - Exit
    """.trimIndent()

    println(introduction);
}