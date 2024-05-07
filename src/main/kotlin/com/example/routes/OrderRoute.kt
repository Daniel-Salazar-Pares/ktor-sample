package com.example.routes

import com.example.models.storage
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.listOrdersRoute() {
    get ("/oder") {
        if (storage.isNotEmpty()) {
            call.respond(storage)
        }
    }
}

fun Route.getOrderRoute() {
    get ("/order/{id?}") {
        val id = call.parameters["id"] ?: return@get call.respondText("Bad request", status = HttpStatusCode.BadRequest)
        val order = storage.find { it.item == id } ?: return@get call.respondText(
            "Not Found",
            status = HttpStatusCode.NotFound
        )
        call.respond(order)
    }
}

fun Route.totalizeOrderRoute () {
    get ("/order/{id?}/total") {
        val id = call.parameters["id"] ?: return@get call.respondText(
            "Bad request",
            status = HttpStatusCode.BadRequest
        )
        val order = storage.find { it.item == id } ?: return@get call.respondText("Not found", status = HttpStatusCode.NotFound)
        val total = order.item.sumOf { it.price * it.amount }
        call.respond(total)
    }
}