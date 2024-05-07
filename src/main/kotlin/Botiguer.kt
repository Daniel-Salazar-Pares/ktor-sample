import com.example.models.Item
import com.example.models.compras
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.util.*
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

fun main () {
    println("Benvingut a Compradon, botiguer!!")
    var exit = false
    val client =  HttpClient(CIO)
    var logged : String? = null
    do {
        logged = loginSeller(client)
    } while (logged == null)

    while (!exit) {
        showBotiguerMenu()
        val opcio = scan.nextLine()
        when (opcio.toInt()) {
            1 -> oferirProducte(logged, client)
            2 -> revisarComandes(client, logged)
            3 -> exit = true
        }
    }
}

@OptIn(InternalAPI::class)
fun oferirProducte(ItemSeller: String, client: HttpClient) {
    println("Quin producte vols oferir?")
    val ItemName = scan.nextLine()
    println("Quin preu vols donar a aquest producte?")
    val ItemPrice = scan.nextDouble()
    scan.nextLine()

    //Now we create an item with our itemInfo
    val item: Item = Item(ItemName, ItemPrice, ItemSeller) //This is an Item
    //Item created in the val item

    runBlocking {
        val json = Json.encodeToString(item)
        val response: HttpResponse = client.post("http://localhost:8080/order/products") {
            contentType(ContentType.Application.Json)
            body = json
        }

        println("Resposta status: ${response.status}")
        println("Resposta cos: ${response.bodyAsText()}")
    }
}

@OptIn(InternalAPI::class)
fun revisarComandes(client : HttpClient, seller: String) {
    runBlocking {
        val json = Json.encodeToString(seller)
        val response: HttpResponse = client.get("http://localhost:8080/seller/comandes") {
            contentType(ContentType.Application.Json)
            body = json
        }

        println("Resposta status: ${response.status}")
        println("Resposta cos: ${response.bodyAsText()}")
    }
}
