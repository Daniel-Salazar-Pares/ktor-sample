import com.example.models.Customer
import com.example.models.Item
import com.example.models.OrderItem
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.client.*
import io.ktor.util.*
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.util.*

val scan = Scanner(System.`in`)
fun showClientMenu () {
    println("1. Llistar els productes que s’ofereixen\n" +
            "2. Fer comandes sobre els productes que s’ofereixen\n" +
            "3. Fer el pagament de la comanda\n" +
            "4. Revisar comandes (llistar les comandes efectuades: botiga, productes, estat de pagament)\n" +
            "5. Sortir")
}

@OptIn(InternalAPI::class)
fun loginClient(client: HttpClient) : String? {
    var logged : String? = null
    println("Has de fer un registre abans de tot!")
    println("Diga'm el teu nom")
    var nom = scan.nextLine()
    var customer = Customer(nom)

    runBlocking {
        val json = Json.encodeToString(customer)
        val response: HttpResponse = client.post("http://localhost:8080/customer") {
            contentType(ContentType.Application.Json)
            body = json
        }

        println("Response status: ${response.status}")
        println("Response body: ${response.bodyAsText()}")
        if (response.status == HttpStatusCode.Created) logged = customer.name
    }

    return logged
}
@OptIn(InternalAPI::class)
fun loginSeller(client: HttpClient) : String? {
    var logged : String? = null
    println("Has de fer un registre abans de tot!")
    println("Diga'm el teu nom")
    var nom = scan.nextLine()
    var seller = nom

    runBlocking {
        val json = Json.encodeToString(seller)
        val response: HttpResponse = client.post("http://localhost:8080/seller") {
            contentType(ContentType.Application.Json)
            body = json
        }

        println("Resposta status: ${response.status}")
        println("Resposta cos: ${response.bodyAsText()}")
        if (response.status == HttpStatusCode.Created) {
            logged = seller
        }
    }

    return logged
}
fun buyProduct(client : HttpClient, nomCustomer : String) {
    println("Diga'm el nom del producte que vols comprar")
    var nomProducte = scan.nextLine()
    println("I quants en vols?")
    var quantitat = scan.nextLine()

    runBlocking {
        val response: HttpResponse = client.post("http://localhost:8080/customer/addProduct/${nomCustomer}/${nomProducte}/${quantitat}")

        println("Resposta status: ${response.status}")
        println("Resposta cos: ${response.bodyAsText()}")
    }
}

fun getProducts(client: HttpClient) {
    runBlocking {
        val response: HttpResponse = client.get("http://localhost:8080/order/products")
        val responseBody = response.bodyAsText()

        println("Resposta status: ${response.status}")
        val products: List<Item> = Json.decodeFromString(responseBody)

        // Now you can work with the list of products
        println("Productes:")
        products.forEach { product ->
            println("Nom: ${product.item}, Preu: ${product.price}, Botiguer: ${product.seller}")
        }
    }
}

fun getComanda(client: HttpClient, nomCustomer: String) {
    runBlocking {
        val response: HttpResponse = client.get("http://localhost:8080/customer/$nomCustomer")
        val responseBody = response.bodyAsText()

        println("Resposta status: ${response.status}")
        val products: List<OrderItem> = Json.decodeFromString(responseBody)

        // Now you can work with the list of products
        println("Productes que ha demanat $nomCustomer:")
        products.forEach { product ->
            println("Nom: ${product.item}, Quantitat: ${product.amount}, Preu: ${product.price}, Botiguer: ${product.seller}")
        }
    }
}

fun showBotiguerMenu () {
    println("1. Oferir producte\n" +
            "2. Revisar comandes\n" +
            "3. Sortir")
}