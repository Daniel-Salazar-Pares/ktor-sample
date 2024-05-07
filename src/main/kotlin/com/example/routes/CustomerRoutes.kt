package com.example.routes

import com.example.models.Customer
import com.example.models.OrderItem
import com.example.models.customerStorage
import com.example.models.storage
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.customerRouting() {
    route("/customer") {
        get {
            if (customerStorage.isNotEmpty()) {
                call.respond(customerStorage)
            } else {
                call.respondText("Cap client registrat", status = HttpStatusCode.OK)
            }
        }
        post {
            val customer = call.receive<Customer>()
            if (customerStorage.any { it.name == customer.name }) {
                call.respondText(
                    "Nom ja registrat : ${customer.name}",
                    status = HttpStatusCode.Conflict
                )
            } else {
                customerStorage.add(customer)
                call.respondText(
                    "Client registrat correctament",
                    status = HttpStatusCode.Created
                )
            }
        }
        post ("/addProduct/{nomCustomer?}/{nomProducte?}/{quantitat?}"){
            val nomProducte = call.parameters["nomProducte"] ?: return@post call.respondText("Bad request", status = HttpStatusCode.BadRequest)
            val nomCustomer = call.parameters["nomCustomer"] ?: return@post call.respondText("Bad request", status = HttpStatusCode.BadRequest)
            val quantitat = call.parameters["quantitat"] ?: return@post call.respondText("Bad request", status = HttpStatusCode.BadRequest)
            val order = storage.find { it.item == nomProducte }
            if (order == null) return@post call.respondText(
                "Product no trobat",
                status = HttpStatusCode.NotFound
            )
            else {
                for (i in 0 until customerStorage.size) {
                    if (customerStorage[i].name == nomCustomer) {
                        customerStorage[i].orderList.add(OrderItem(nomProducte, quantitat.toInt(), quantitat.toInt()*order.price, order.seller, nomCustomer))
                    }
                }
                return@post call.respondText(
                    "Producte ${order.item} registrat a nom de $nomCustomer",
                    status = HttpStatusCode.NotFound
                )
            }
        }
        post () {
            val customer = call.receive<Customer>()
            if (customerStorage.any { it.name == customer.name }) {
                call.respondText(
                    "Nom ja registrat : ${customer.name}",
                    status = HttpStatusCode.Conflict
                )
            } else {
                customerStorage.add(customer)
                call.respondText(
                    "Client registrat correctament",
                    status = HttpStatusCode.Created
                )
            }
        }
        get ("/products") {
            if (storage.isNotEmpty()) {
                call.respond(storage)
            }
        }
        get("/{nomCustomer?}") {
            val nomCustomer = call.parameters["nomCustomer"] ?: return@get call.respondText("Bad request", status = HttpStatusCode.BadRequest)
            for (i in 0 until customerStorage.size) {
                if (customerStorage[i].name == nomCustomer) {
                    call.respond(customerStorage[i].orderList)
                }
            }
        }
    }
}
