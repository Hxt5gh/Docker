package com.hxt5gh.plugins

import com.hxt5gh.Routes.customerRout
import com.hxt5gh.models.getOrderById
import com.hxt5gh.models.getTotalPrice
import com.hxt5gh.models.listAllOrders
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        customerRout()
        listAllOrders()
        getOrderById()
        getTotalPrice()
       get("/"){
           call.respondText("Hello World Docker Working!")
       }
    }
}



//laptopharpreet
//fz0LA6MmqxHhk2xE
