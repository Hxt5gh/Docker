package com.hxt5gh.Routes

import com.hxt5gh.models.Customers
import com.hxt5gh.models.customerData
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlin.text.get


fun Route.customerRout()
{
    route("/customer"){

        get{
            //returning respond
            if (customerData.isNotEmpty())
            {
                call.respond(customerData)
            }else{
                call.respondText("No Data Found..." , status = HttpStatusCode.OK)
            }
        //            using the call.respond function in Ktor, which can take a Kotlin
        //            object and return it serialized in a specified format.

        }
        get("{id?}") {
            val id = call.parameters["id"] ?: return@get call.respondText(
                "Missing id",
                status = HttpStatusCode.BadRequest
            )// first check id if not exist return bad request
            val customer =
                customerData.find { it.id == id } ?: return@get call.respondText(
                    "No customer with id $id",
                    status = HttpStatusCode.NotFound
                )
            call.respond(customer)

        }

        post {
            val customer = call.receive<Customers>()
            customerData.add(customer)
            call.respondText("Customer stored correctly", status = HttpStatusCode.Created)
        }
        delete("{id?}") {
            val id = call.parameters["id"] ?: return@delete call.respond(HttpStatusCode.BadRequest)
            if (customerData.removeIf { it.id == id }) {
                call.respondText("Customer removed correctly", status = HttpStatusCode.Accepted)
            } else {
                call.respondText("Not Found", status = HttpStatusCode.NotFound)
            }
        }
    }
}

//In this case, we're using the route function to group everything
// that falls under the /customer endpoint. We then create a block for
// each HTTP method. This is just one approach how we can structure our
// routes – when we tackle the Order routes in the next chapter, we will see another approach.


//get: one without a path parameter, and the other with {id?}. We'll use the first entry to
// list all customers, and the second entry to display a specific one.