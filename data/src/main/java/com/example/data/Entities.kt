package com.example.data.com.example.data

import java.util.*
import java.util.function.DoubleBinaryOperator
import kotlin.collections.ArrayList

class History{
    var updateDate = Date().time
}

data class UncompletedOrder(val id: Int, val name:String, val order: ArrayList<OrderItem>)

class CompletedOrder(var completeOrderList: ArrayList<CompletedOrder>)

data class OrderItem(
    var name:String,
    var units: String,
    var count: Double
){
    var price: Double = 0.0
    var totalCount: Double = 0.0
    var fp = { totalCount * price }

}

