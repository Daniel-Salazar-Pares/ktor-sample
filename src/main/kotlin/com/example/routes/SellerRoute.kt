package com.example.routes

import com.example.models.OrderItem
import com.example.models.compras
import com.example.models.sellerStorage
import com.example.models.storage
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.sellerRouting() {
    route("/seller") {
        get {
            if (sellerStorage.isNotEmpty()) {
                call.respond(sellerStorage)
            } else {
                call.respondText("No hi ha cap botiguer", status = HttpStatusCode.OK)
            }
        }
        post {
            val seller = call.receive<String>()
            if (sellerStorage.any { it == seller }) {
                call.respondText(
                    "Nom ja registrat : $seller",
                    status = HttpStatusCode.Conflict
                )
            } else {
                sellerStorage.add(seller)
                call.respondText(
                    "Botiguer posat",
                    status = HttpStatusCode.Created
                )
            }
        }
        get ("/products") {
            if (storage.isNotEmpty()) {
                call.respond(storage)
            }
        }
        get ("/comandes") {
            val seller = call.receive<String>()
            var comandes = mutableListOf<OrderItem>()
            for (i in compras) {
                if (i.seller == seller) comandes.add(i)
            }
            if (comandes.isNotEmpty()) call.respond(comandes)
            else {
                call.respondText("Cap comanda trobada", status = HttpStatusCode.OK)
            }
        }
    }
}
