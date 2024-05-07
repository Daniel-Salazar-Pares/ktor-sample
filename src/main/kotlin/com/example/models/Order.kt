package com.example.models

import kotlinx.serialization.Serializable

@Serializable
data class OrderItem(val item: String, val amount: Int, val price: Double, val seller: String, val buyer : String, var state : String = "Pending")

@Serializable
data class Item(val item: String, val price: Double, val seller: String)

val compras = mutableListOf(
        OrderItem("Aigua", 1, 10.0, "Pere", "Juan", "Comprat"),
        OrderItem("awd", 1, 10.0, "PEPE", "Juan", "Comprat")
)

val storage = mutableListOf(
        Item("Entrepa de jamon",  5.50, "PEPE"),
        Item("Aigua",  1.50, "Pere"),
        Item("Cervessa",  2.30, "PEPE"),
        Item("Cheesecake",  3.75, "Pere"),
        Item("Hamburguesa", 8.50, "PEPE"),
        Item("Clara",  1.50, "Pere"),
        Item("Coca cola",  1.76, "PEPE"),
        Item("Gelat",  2.35, "PEPE")
)