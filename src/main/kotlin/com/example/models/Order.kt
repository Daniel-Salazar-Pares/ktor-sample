package com.example.models

import kotlinx.serialization.Serializable

@Serializable
data class OrderItem(val item: String, val amount: Int, val price: Double)

@Serializable
data class Item(val item: String, val price: Double)


val storage = listOf(
        Item("Ham Sandwich",  5.50),
        Item("Water",  1.50),
        Item("Beer",  2.30),
        Item("Cheesecake",  3.75),
        Item("Cheeseburger", 8.50),
        Item("Water",  1.50),
        Item("Coke",  1.76),
        Item("Ice Cream",  2.35)
)