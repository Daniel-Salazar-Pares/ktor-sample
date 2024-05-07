import io.ktor.client.engine.cio.*
import io.ktor.client.*

fun main () {
    val client = HttpClient(CIO)
    println("Hola, benvingut a Compradon.")
    var logged : String? = null
    do {
        logged = loginClient(client)
    } while (logged == null)
    var sortir = false
    var opcio = ""
    do {
        showClientMenu()
        opcio = scan.nextLine()
        when (opcio.toInt()) {
            1 -> getProducts(client)
            2 -> buyProduct(client, logged)
            3 -> {}
            4 -> getComanda(client, logged)
            5 -> sortir = true
            else -> println("Opció invalida")
        }
    } while (!sortir)
}

