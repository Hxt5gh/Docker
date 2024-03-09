package com.hxt5gh.models

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

//GET http://127.0.0.1:8080/order
//Content-Type: application/json
//
//###
//GET http://127.0.0.1:8080/order/2020-04-06-01
//Content-Type: application/json
//
//###
//GET http://127.0.0.1:8080/order/2020-04-06-01/total
//Content-Type: application/json

fun Route.listAllOrders()
{

        get("/order")
        {
            if (orderStorage.isNotEmpty()){
                call.respond(orderStorage)
            }else
            {
                call.respondText("No order data Found" , status = HttpStatusCode.NotFound)
            }
        }

}

fun Route.getOrderById()
{
    get("/order/{id?}")
    {
        val id = call.parameters["id"] ?: return@get call.respondText("Bad Request", status = HttpStatusCode.BadRequest)

//        val order = orderStorage.find { it.number == id } ?: return@get
//        call.respondText("Not Found", status = HttpStatusCode.NotFound)

        val order = orderStorage.find { it.number == id } ?: return@get call.respondText(
            "Not Found",
            status = HttpStatusCode.NotFound
        )
        call.respond(order)
    }
}

fun Route.getTotalPrice()
{
     get("/order/{id?}/total"){
//         val id = call.parameters["id"] ?: return@get
//         call.respondText("Bad Request", status = HttpStatusCode.BadRequest)
//
//         val order = orderStorage.find { it.number == id } ?: return@get
//         call.respondText("Not Found", status = HttpStatusCode.NotFound)
//
//         val total = order.contents.sumOf { it.price * it.amount }
//         call.respond(total)

         val id = call.parameters["id"] ?: return@get call.respondText("Bad Request", status = HttpStatusCode.BadRequest)
         val order = orderStorage.find { it.number == id } ?: return@get call.respondText(
             "Not Found",
             status = HttpStatusCode.NotFound
         )

         val total = order.contents.sumOf { it.price * it.amount }
         call.respond(total)
     }
}