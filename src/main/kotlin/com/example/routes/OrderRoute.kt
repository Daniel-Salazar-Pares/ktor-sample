package com.example.routes

import com.example.models.Item
import com.example.models.storage
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.listOrdersRoute() {
    get("/order/products") {
        if (storage.isNotEmpty()) {
            call.respond(storage)
        }
    }
}

fun Route.postOrderRoute() {
    post("/order/products") {
        val product = call.receive<Item>()
        if (storage.any { it.item == product.item }) {
            call.respondText(
                "Producte ja registrat : ${product.item}",
                status = HttpStatusCode.Conflict
            )
        } else {
            storage.add(product)
            call.respondText(
                "Producte registrat correctament",
                status = HttpStatusCode.Created
            )
        }

    }
}
