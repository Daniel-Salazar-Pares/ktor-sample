package com.example.models

import kotlinx.serialization.Serializable

@Serializable
data class Customer(val name : String, val orderList: MutableList<OrderItem> = mutableListOf())

val customerStorage = mutableListOf<Customer>()