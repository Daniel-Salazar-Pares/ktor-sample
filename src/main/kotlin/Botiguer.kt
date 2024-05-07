fun main () {
    println("Benvingut a Compradon, botiguer!!")
    var exit = false
    while (!exit) {
        showBotiguerMenu()
        val opcio = scan.nextInt()
        when (opcio) {
            1 -> oferirProducte()
            2 -> revisarComandes()
            3 -> exit = true
        }
    }
}

fun oferirProducte() {
    TODO("Not yet implemented")
}

fun revisarComandes() {
    TODO("Not yet implemented")
}
